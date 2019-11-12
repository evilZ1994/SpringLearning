package ishavanti.jdbc.demo1;

import ishavanti.jdbc.util.JDBCUtil;
import org.junit.Test;

import java.sql.*;

public class JDBCDemo2 {

    @Test
    /**
     * 保存操作
     */
    public void create() {
        Connection connection = null;
        Statement statement = null;
        try {
            // 注册驱动
            // 获取连接
            connection = JDBCUtil.getConnection();
            // 创建Statement对象
            statement = connection.createStatement();
            // 编写SQL
            String sql = "insert into user values (null, 'eee', '123', '王六')";
            // 执行SQL
            int i = statement.executeUpdate(sql);
            if (i > 0) {
                System.out.println("添加成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            JDBCUtil.release(statement, connection);
        }
    }

    @Test
    /**
     * 修改操作
     */
    public void update() {
        Connection connection = null;
        Statement statement = null;
        try {
            // 注册驱动，获取连接
            connection = JDBCUtil.getConnection();
            // 创建Statement对象
            statement = connection.createStatement();
            // 编写SQL
            String sql = "update user set name='六六' where uid=4";
            // 执行SQL
            int i = statement.executeUpdate(sql);
            if (i > 0) {
                System.out.println("修改成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            JDBCUtil.release(statement, connection);
        }
    }

    @Test
    /**
     * 删除操作
     */
    public void delete() {
        Connection connection = null;
        Statement statement = null;
        try {
            // 注册驱动，获取连接
            connection = JDBCUtil.getConnection();
            // 创建Statement对象
            statement = connection.createStatement();
            // 编写SQL
            String sql = "delete from user where uid=4";
            // 执行SQL
            int i = statement.executeUpdate(sql);
            if (i > 0) {
                System.out.println("删除成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            JDBCUtil.release(statement, connection);
        }
    }

    @Test
    /**
     * 查询多条数据
     */
    public void queryAll() {
        Connection connection = null;
        Statement statement = null;
        ResultSet res = null;
        try {
            // 注册驱动，获取连接
            connection = JDBCUtil.getConnection();
            // 创建Statement对象
            statement = connection.createStatement();
            // 编写SQL
            String sql = "select * from user";
            // 执行SQL
            res = statement.executeQuery(sql);
            while (res.next()) {
                System.out.println(res.getInt("uid") + " " + res.getString("username") + " " +
                        res.getString("password") + " " + res.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            JDBCUtil.release(res, statement, connection);
        }
    }

    @Test
    /**
     * 查询单条数据
     */
    public void queryOne() {
        Connection connection = null;
        Statement statement = null;
        ResultSet res = null;
        try {
            // 注册驱动，获取连接
            connection = JDBCUtil.getConnection();
            // 创建Statement对象
            statement = connection.createStatement();
            // 编写SQL
            String sql = "select * from user where uid=1";
            // 执行SQL
            res = statement.executeQuery(sql);
            if (res.next()) {
                System.out.println(res.getInt("uid") + " " + res.getString("username") + " " +
                        res.getString("password") + " " + res.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            JDBCUtil.release(res, statement, connection);
        }
    }
}
