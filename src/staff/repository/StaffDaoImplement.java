package staff.repository;

import global.db.DBConnection;
import staff.entity.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffDaoImplement {


    public Staff login(int userId, int password) {
        Staff staff = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT user_id, password, user_name FROM STAFF WHERE user_id = ? AND password = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, password);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                staff = new Staff();
                staff.setUserId(resultSet.getInt("user_id"));
                staff.setPassword(resultSet.getInt("password"));
                staff.setUserName(resultSet.getString("user_name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace(); // 로깅 또는 무시
            }

            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return staff;
    }
}