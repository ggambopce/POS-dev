package stock;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StockDao {

    /**
     * 재고 차감을 위해 유통기한 내 재고를 수량조건에 맞게 조회
     * 유통기한이 지나지 않았고 수량이 남아 있는 재고를 수량 조건에 맞게 정렬하여 반환
     * @param productId
     * @param quantity
     * @return
     */
    public List<Stock> findAvailableStocksByProductId(int productId, int quantity) {
        List<Stock> available = new ArrayList<>();
        String sql = """
            SELECT stock_id, product_id, quantity, expiry_date, received_date
            FROM STOCK
            WHERE product_id = ? AND expiry_date >= SYSDATE AND quantity > 0
            ORDER BY expiry_date
        """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                int remaining = quantity;
                while (rs.next() && remaining > 0) {
                    int qty = rs.getInt("quantity");
                    int usable = Math.min(qty, remaining);

                    Stock stock = new Stock(
                            rs.getInt("stock_id"),
                            rs.getInt("product_id"),
                            qty,
                            rs.getDate("expiry_date"),
                            rs.getDate("received_date")
                    );

                    available.add(stock);
                    remaining -= usable;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("재고 조회 실패", e);
        }

        return available;
    }

    /**
     * 특정 재고 항목의 수량을 갱신
     */
    public void updateQuantity(int stockId, int newQuantity) {
        String sql = "UPDATE STOCK SET quantity = ? WHERE stock_id = ?";
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, newQuantity);
            ps.setInt(2, stockId);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                System.out.printf("재고 ID %d 업데이트 실패%n", stockId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("재고 수량 업데이트 실패", e);
        }
    }

    /**
     * 입고 등록
     * 입고 들어오자마자 등록한다고 가정
     * 입고 목록에 따라 사용자가 입고 등록을 한다.
     * 1개씩 등록 가능
     * @param productId
     * @param quantity
     * @param expiryDate
     */
    public void saveStock(int productId, int quantity, Date expiryDate) {
        String insertSql = """
            INSERT INTO STOCK (stock_id, product_id, received_date, expiry_date, quantity)
            VALUES (STOCK_SEQ.NEXTVAL, ?, SYSDATE, ?, ?)
        """;

        String updateStockSql = """
            UPDATE PRODUCT
            SET stock = (
                SELECT NVL(SUM(quantity), 0)
                FROM STOCK
                WHERE product_id = ?
                  AND expiry_date >= SYSDATE
            )
            WHERE product_id = ?
        """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                PreparedStatement updateStockStmt = conn.prepareStatement(updateStockSql)
        ) {
            conn.setAutoCommit(false);

            // 1. STOCK 테이블에 추가
            insertStmt.setInt(1, productId);
            insertStmt.setDate(2, new java.sql.Date(expiryDate.getTime()));
            insertStmt.setInt(3, quantity);
            insertStmt.executeUpdate();

            // 2. PRODUCT.stock 재계산
            updateStockStmt.setInt(1, productId);
            updateStockStmt.setInt(2, productId);
            updateStockStmt.executeUpdate();

            conn.commit();

        } catch (Exception e) {
            throw new RuntimeException("입고 등록 실패", e);
        }
    }
}
