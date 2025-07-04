package product;

import db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    /** 제품 등록
     * 제품을 등록합니다
     * 제품 아이디와 제품 이름으로 중복검사 필요!!
     * @param product
     */
    public void saveProduct(Product product) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.getConnection();
            String sql = """
                INSERT INTO PRODUCT (
                    product_id,
                    product_name,
                    manufacturer,
                    expiry_date,
                    adult_only,
                    price,
                    received_date,
                    stock
                ) VALUES (
                    PRODUCT_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?
                )
            """;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getManufacturer());
            preparedStatement.setTimestamp(3, new Timestamp(product.getExpiryDate().getTime()));
            preparedStatement.setString(4, String.valueOf(product.getAdultOnly()));
            preparedStatement.setInt(5, product.getPrice());
            preparedStatement.setTimestamp(6, new Timestamp(product.getReceivedDate().getTime()));
            preparedStatement.setInt(7, product.getStock());

            int row = preparedStatement.executeUpdate();
            System.out.printf("%s : %d\r\n", "저장된 행", row);
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.printf("%s\r\n", "close() 오류");
            }
        }
    }

    /** 제품 목록 조회
     * 제품 전체 목록을 리스트에 담아서 조회 합니다.
     * @return
     */
    public List<Product> findAllProducts() {
        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM PRODUCT ORDER BY product_id";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("product_id"),
                        resultSet.getString("product_name"),
                        resultSet.getString("manufacturer"),
                        resultSet.getTimestamp("expiry_date"),
                        resultSet.getString("adult_only").charAt(0),
                        resultSet.getInt("price"),
                        resultSet.getTimestamp("received_date"),
                        resultSet.getInt("stock")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException("제품 목록 조회 실패", e);
        }

        return products;
    }

    public Product findByName(String productName) {
        Product product = null;
        String sql = "SELECT * FROM PRODUCT WHERE product_name = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, productName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    product = new Product(
                            resultSet.getInt("product_id"),
                            resultSet.getString("product_name"),
                            resultSet.getString("manufacturer"),
                            resultSet.getTimestamp("expiry_date"),
                            resultSet.getString("adult_only").charAt(0),
                            resultSet.getInt("price"),
                            resultSet.getTimestamp("received_date"),
                            resultSet.getInt("stock")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("제품 검색 실패", e);
        }

        return product;
    }

    /**
     * 제품 재고를 수정합니다.
     * @param productId 제품 ID
     * @param newStock 수정할 재고 수량
     */
    public void updateStock(int productId, int newStock) {
        String sql = "UPDATE PRODUCT SET stock = ? WHERE product_id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, newStock);
            preparedStatement.setInt(2, productId);

            int row = preparedStatement.executeUpdate();
            System.out.printf("재고 수정 완료 (수정된 행 수: %d)\n", row);

        } catch (SQLException e) {
            throw new RuntimeException("재고 수정 실패", e);
        }
    }

    // ID로 제품 조회
    public Product findById(int productId) {
        Product product = null;
        String sql = "SELECT * FROM PRODUCT WHERE product_id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, productId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    product = new Product(
                            resultSet.getInt("product_id"),
                            resultSet.getString("product_name"),
                            resultSet.getString("manufacturer"),
                            resultSet.getTimestamp("expiry_date"),
                            resultSet.getString("adult_only").charAt(0),
                            resultSet.getInt("price"),
                            resultSet.getTimestamp("received_date"),
                            resultSet.getInt("stock")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("제품 ID 조회 실패", e);
        }

        return product;
    }
}
