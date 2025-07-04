package stock;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StockDao {

    public boolean reduceStock(int productId, int quantity) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM STOCK WHERE product_id = ? AND expiry_date >= SYSDATE AND quantity > 0 ORDER BY expiry_date";
        PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ps.setInt(1, productId);
        ResultSet rs = ps.executeQuery();

        int remaining = quantity;

        while (rs.next() && remaining > 0) {
            int stockQty = rs.getInt("quantity");
            int deduct = Math.min(stockQty, remaining);

            rs.updateInt("quantity", stockQty - deduct);
            rs.updateRow();

            remaining -= deduct;
        }

        rs.close();
        ps.close();
        conn.close();

        return remaining == 0; // true면 성공, false면 재고 부족
    }

}
