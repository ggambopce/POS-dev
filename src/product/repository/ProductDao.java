package product.repository;

import global.db.DBConnection;
import product.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    /** 새로운 제품 등록
     * 제품을 등록합니다
     * 유통기한과, 받은 날짜는 재고테이블에서 관리
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
                    adult_only,
                    price
                ) VALUES (
                    PRODUCT_SEQ.NEXTVAL, ?, ?, ?, ?
                )
            """;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getManufacturer());
            preparedStatement.setString(3, String.valueOf(product.getAdultOnly()));
            preparedStatement.setInt(4, product.getPrice());

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
     * 재고는 재고 테이블에서 유통기한이 지나지 않은 물건의 갯수만 반영합니다.
     * @return
     */
    public List<Product> findAllProducts() {
        List<Product> products = new ArrayList<>();

        String sql = """
            SELECT
                p.product_id,
                p.product_name,
                p.manufacturer,
                p.adult_only,
                p.price,
                p.stock
            FROM PRODUCT p
            ORDER BY p.product_id
        """;
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
                        resultSet.getString("adult_only").charAt(0),
                        resultSet.getInt("price"),
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
        String sql = """
            SELECT
                p.product_id,
                p.product_name,
                p.manufacturer,
                p.adult_only,
                p.price,
                p.stock
            FROM PRODUCT p
            WHERE p.product_name = ?
        """;

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
                            resultSet.getString("adult_only").charAt(0),
                            resultSet.getInt("price"),
                            resultSet.getInt("stock")   // // 유통기한이 지나지 않은 재고 수량
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("제품 검색 실패", e);
        }

        return product;
    }

    // ID로 제품 조회
    public Product findById(int productId) {
        Product product = null;
        String sql = """
            SELECT
                p.product_id,
                p.product_name,
                p.manufacturer,
                p.adult_only,
                p.price,
                p.stock
            FROM PRODUCT p
            WHERE p.product_id = ?
        """;

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
                            resultSet.getString("adult_only").charAt(0),
                            resultSet.getInt("price"),
                            resultSet.getInt("stock")       //계산된 유통기한 내 재고 수량
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("제품 ID 조회 실패", e);
        }

        return product;
    }

}
