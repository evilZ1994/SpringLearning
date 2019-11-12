package ishavanti.jdbc.demo1;

import org.junit.Test;

import java.sql.*;

public class JDBCDemo1 {

    @Test
    /**
     * JDBC入门程序
     */
    public void demo1() {
        Connection connection = null;
        Statement statement = null;
        ResultSet res = null;
        try {
            // 1. 加载驱动
            // DriverManager.registerDriver(new Driver()); // 这种方式会注册两次驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2. 建立连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctest", "root", "cdhzxb2019");
            // 3. 创建执行SQL语句的对象，并且执行SQL
            // 3.1 创建Statement对象
            statement = connection.createStatement();
            // 3.2 执行SQl语句，获取结果集ResultSet
            String sql = "select * from user";
            res = statement.executeQuery(sql);
            while (res.next()) {
                int uid = res.getInt("uid");
                String username = res.getString("username");
                String password = res.getString("password");
                String name = res.getString("name");

                System.out.println(uid + " " + username + " " + password + " " + name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 4. 释放资源
            if (res != null) {
                try {
                    res.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                res = null;
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                statement = null;
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                connection = null;
            }
        }
    }
}
