package staff;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffDao {

    public Staff login(int userId, int password) {
        Staff staff = null;
        Connection connection = DBConnection.getConnection();

        try {
            final String sql = "SELECT user_id, password, user_name FROM STAFF WHERE user_id = ? AND password = ?";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, password);

            final ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                staff = new Staff();
                staff.setUserId(resultSet.getInt("user_id"));
                staff.setPassword(resultSet.getInt("password"));
                staff.setUserName(resultSet.getString("user_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return staff;
    }
}
