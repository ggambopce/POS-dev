package purchase;

import db.DBConnection;

import java.sql.*;
import java.util.List;

public class PurchaseDao {
    /**
     * 구매 서비스 로직
     * @param purchase
     * @param details
     */
    public void savePurchase(Purchase purchase, List<PurchaseDetail> details) {
        Connection conn = null;
        PreparedStatement purchaseStmt = null;
        PreparedStatement detailStmt = null;
        PreparedStatement priceStmt = null;
        /**
         * 두 개의 테이블을 하나의 로직에서 작업하기때문에 트랜잭션 사용
         */
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // 트랜젝션 시작

            // 0. totalAmount 계산
            String priceSql = "SELECT price FROM PRODUCT WHERE product_id = ?";
            priceStmt = conn.prepareStatement(priceSql);

            int totalAmount = 0;
            for (PurchaseDetail detail : details) {
                priceStmt.setInt(1, detail.getProductId());
                try (ResultSet rs = priceStmt.executeQuery()) {
                    if (rs.next()) {
                        int unitPrice = rs.getInt("price");
                        totalAmount += unitPrice * detail.getPurchaseQuantity();
                    } else {
                        throw new RuntimeException("상품 가격 조회 실패: " + detail.getProductId());
                    }
                }
            }

            // 1. Purchase 저장
            String purchaseSql = """
                INSERT INTO PURCHASE (purchase_id, purchase_time, total_amount)
                VALUES (PURCHASE_SEQ.NEXTVAL, ?, ?)
            """;

            purchaseStmt = conn.prepareStatement(purchaseSql, new String[]{"purchase_id"});
            purchaseStmt.setTimestamp(1, new Timestamp(purchase.getPurchaseTime().getTime()));
            purchaseStmt.setInt(2, totalAmount);
            purchaseStmt.executeUpdate();

            // 2. 생성된 purchase_id 얻기
            ResultSet rs = purchaseStmt.getGeneratedKeys();
            int generatedPurchaseId = 0;
            if (rs.next()) {
                generatedPurchaseId = rs.getInt(1);
            } else {
                throw new RuntimeException("구매 ID 생성 실패");
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
            try { if (priceStmt != null) priceStmt.close(); } catch (Exception ignored) {}
            try { if (detailStmt != null) detailStmt.close(); } catch (Exception ignored) {}
            try { if (purchaseStmt != null) purchaseStmt.close(); } catch (Exception ignored) {}
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }
    }

}

