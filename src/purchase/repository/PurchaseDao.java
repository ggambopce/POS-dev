package purchase.repository;

import global.db.DBConnection;
import purchaseDetail.entity.PurchaseDetail;
import purchase.entity.Purchase;

import java.sql.*;
import java.util.List;

public class PurchaseDao {
    /**
     * 구매 서비스 로직
     * Purchase 클래스 totalAmount에 반영
     * PurchaseDetail 클래스에 반영
     * Stock 클래스의 유통기한이 남은 것중 가장 현재날까에 가까운 quantity 에서 차감
     * Product 클래스 stock 하락 반영
     * @param purchase
     * @param details
     */
    public void savePurchase(Purchase purchase, List<PurchaseDetail> details) {
        Connection conn = null;
        PreparedStatement purchaseStmt = null;
        PreparedStatement detailStmt = null;
        PreparedStatement priceStmt = null;
        PreparedStatement stockUpdateStmt = null;

        /**
         * 여러개의 테이블을 하나의 로직에서 작업하기때문에 트랜잭션 사용
         */
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // 트랜젝션 시작


            // 1. Purchase 저장
            String purchaseSql = """
                INSERT INTO PURCHASE (purchase_id, purchase_time, total_amount)
                VALUES (PURCHASE_SEQ.NEXTVAL, ?, ?)
            """;

            purchaseStmt = conn.prepareStatement(purchaseSql);
            purchaseStmt.setTimestamp(1, new Timestamp(purchase.getPurchaseTime().getTime()));
            purchaseStmt.setInt(2, purchase.getTotalAmount());
            purchaseStmt.executeUpdate();

            // 2. 생성된 purchase_id 얻기 (Oracle 방식)
            int generatedPurchaseId = 0;
            try (PreparedStatement seqStmt = conn.prepareStatement("SELECT PURCHASE_SEQ.CURRVAL FROM DUAL");
                 ResultSet rs = seqStmt.executeQuery()) {
                if (rs.next()) {
                    generatedPurchaseId = rs.getInt(1);
                } else {
                    throw new RuntimeException("구매 ID 생성 실패: CURRVAL 조회 실패");
                }
            }

            // 3. PurchaseDetail 저장
            String detailSql = """
                INSERT INTO PURCHASE_DETAIL (
                    purchase_detail_id,
                    purchase_quantity,
                    purchase_id,
                    product_id,
                    stock_id
                ) VALUES (
                    PURCHASE_DETAIL_SEQ.NEXTVAL, ?, ?, ?, ?
                )
            """;
            detailStmt = conn.prepareStatement(detailSql);

            for (PurchaseDetail detail : details) {
                detailStmt.setInt(1, detail.getPurchaseQuantity());
                detailStmt.setInt(2, generatedPurchaseId);
                detailStmt.setInt(3, detail.getProductId());
                detailStmt.setInt(4, detail.getStockId());
                detailStmt.addBatch();
            }

            detailStmt.executeBatch();

            // 4. PRODUCT.stock 감소 처리 추가
            String stockUpdateSql = "UPDATE PRODUCT SET stock = stock - ? WHERE product_id = ?";
            stockUpdateStmt = conn.prepareStatement(stockUpdateSql);
            for (PurchaseDetail detail : details) {
                stockUpdateStmt.setInt(1, detail.getPurchaseQuantity());
                stockUpdateStmt.setInt(2, detail.getProductId());
                stockUpdateStmt.addBatch();
            }
            stockUpdateStmt.executeBatch();

            //5. STOCK.quantity 유통기한이 안지난 것중에 가장 빠른것 감소 처리 추가
            String quantityUpdateSql = "UPDATE STOCK SET quantity = quantity - ? WHERE stock_id = ?";
            PreparedStatement stockQuantityStmt = conn.prepareStatement(quantityUpdateSql);
            for (PurchaseDetail detail : details) {
                stockQuantityStmt.setInt(1, detail.getPurchaseQuantity());
                stockQuantityStmt.setInt(2, detail.getStockId());
                stockQuantityStmt.addBatch();
            }
            stockQuantityStmt.executeBatch();


            conn.commit(); // 트랜잭션 커밋
            System.out.println("구매 및 상세 저장 완료");

        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("롤백 완료");
                } catch (SQLException ex) {
                    throw new RuntimeException("롤백 중 오류", ex);
                }
            }
            throw new RuntimeException("구매 저장 실패", e);
        } finally {
            try { if (stockUpdateStmt != null) stockUpdateStmt.close(); } catch (Exception ignored) {}
            try { if (priceStmt != null) priceStmt.close(); } catch (Exception ignored) {}
            try { if (detailStmt != null) detailStmt.close(); } catch (Exception ignored) {}
            try { if (purchaseStmt != null) purchaseStmt.close(); } catch (Exception ignored) {}
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }
    }

}

