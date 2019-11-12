package ishavanti.jdbc.demo2;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import ishavanti.jdbc.util.JDBCUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 连接池的测试类
 */
public class DataSourceDemo1 {

    @Test
    /**
     * 手动设置连接池
     */
    public void demo1() {
        Connection connection = null;
        PreparedStatement preStatement = null;
        ResultSet res = null;
        try {
            // 创建连接池
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            // 设置连接池参数
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/jdbctest");
            dataSource.setUser("root");
            dataSource.setPassword("root");
            // 设置其他参数
            dataSource.setMaxPoolSize(20);
            dataSource.setInitialPoolSize(3);
            // 获得连接
            connection = dataSource.getConnection();
            // 编写SQL
            String sql = "select * from user";
            // 预编译SQL
            preStatement = connection.prepareStatement(sql);
            // 执行SQL
            res = preStatement.executeQuery();
            while (res.next()) {
                System.out.println(res.getInt("uid") + " " + res.getString("username") + " " +
                        res.getString("password") + " " + res.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.release(res, preStatement, connection);
        }
    }

    @Test
    /**
     * 使用配置文件的方式
     */
    public void demo2() {
        Connection connection = null;
        PreparedStatement preStatement = null;
        ResultSet res = null;
        try {
            // 创建连接池
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            // 获得连接
            connection = dataSource.getConnection();
            // 编写SQL
            String sql = "select * from user";
            // 预编译SQL
            preStatement = connection.prepareStatement(sql);
            // 执行SQL
            res = preStatement.executeQuery();
            while (res.next()) {
                System.out.println(res.getInt("uid") + " " + res.getString("username") + " " +
                        res.getString("password") + " " + res.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.release(res, preStatement, connection);
        }
    }
}
