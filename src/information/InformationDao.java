package information;

import db.DBConnection;

import java.sql.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class InformationDao {

    // 로그인 시 저장되는 정보
    public void saveLoginInfo(int userId, Date now) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "INSERT INTO INFORMATION (information_id, user_id, login_time) VALUES (INFORMATION_SEQ.NEXTVAL,?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setTimestamp(2, new Timestamp(now.getTime()));

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

    // 퇴근 시 근무 시간 계산 및 일당 출력
    public void finishWork(int userId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getConnection();

            // 가장 최근 login_time 가져오기
            String sql =  """
                SELECT i.login_time, s.user_name
                FROM INFORMATION i
                JOIN STAFF s ON i.user_id = s.user_id
                WHERE i.user_id = ?
                ORDER BY i.login_time DESC
                FETCH FIRST 1 ROWS ONLY
            """;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Timestamp loginTime = resultSet.getTimestamp("login_time");
                String userName = resultSet.getString("user_name");
                Timestamp logoutTime = new Timestamp(System.currentTimeMillis());

                long workMillis = logoutTime.getTime() - loginTime.getTime();
                long workMinutes = TimeUnit.MILLISECONDS.toMinutes(workMillis);

                int wagePerMinute = 11000 / 60;
                int dailyPay = (int) workMinutes * wagePerMinute;

                // 출력
                System.out.printf("사원 : %s 빠이.%n", userName);
                System.out.printf("분당 시급 : %,d 원%n", wagePerMinute);
                System.out.printf("총 근무 시간 : %d분%n", workMinutes);
                System.out.printf("오늘 일당 : %,d 원%n", dailyPay);
            } else {
                System.out.println("로그인 기록을 찾을 수 없습니다.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("퇴근 처리 중 오류 발생", e);
        } finally {
            try { if (resultSet != null) resultSet.close(); } catch (Exception ignored) {}
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception ignored) {}
            try { if (connection != null) connection.close(); } catch (Exception ignored) {}
        }
    }
}
