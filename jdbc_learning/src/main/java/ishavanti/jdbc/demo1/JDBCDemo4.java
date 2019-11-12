package ishavanti.jdbc.demo1;

import ishavanti.jdbc.util.JDBCUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * PreparedStatement的基本用法
 */
public class JDBCDemo4 {

    @Test
    /**
     * 保存数据
     */
    public void save() {
        Connection connection = null;
        PreparedStatement preStatement = null;
        try {
            // 注册驱动，获取连接
            connection = JDBCUtil.getConnection();
            // 编写SQL
            String sql = "insert into user values (null, ?, ?, ?)";
            // 预处理SQL
            preStatement = connection.prepareStatement(sql);
            // 设置参数
            preStatement.setString(1, "fff");
            preStatement.setString(2, "123");
            preStatement.setString(3, "小七");
            // 执行SQL
            int num = preStatement.executeUpdate();
            if (num > 0) {
                System.out.println("保存成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            JDBCUtil.release(preStatement, connection);
        }
    }

    @Test
    /**
     * 修改数据
     */
    public void update() {
        Connection connection = null;
        PreparedStatement preStatement = null;
        try {
            // 注册驱动，获取连接
            connection = JDBCUtil.getConnection();
            // 编写SQL
            String sql = "update user set name=? where uid=?";
            // 预处理SQL
            preStatement = connection.prepareStatement(sql);
            // 设置参数
            preStatement.setString(1, "小七2");
            preStatement.setInt(2, 6);
            // 执行SQL
            int num = preStatement.executeUpdate();
            if (num > 0) {
                System.out.println("修改成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            JDBCUtil.release(preStatement, connection);
        }
    }

    @Test
    /**
     * 删除数据
     */
    public void delete() {
        Connection connection = null;
        PreparedStatement preStatement = null;
        try {
            // 注册驱动，获取连接
            connection = JDBCUtil.getConnection();
            // 编写SQL
            String sql = "delete from user where uid=?";
            // 预编译SQL
            preStatement = connection.prepareStatement(sql);
            // 设置参数
            preStatement.setInt(1, 6);
            // 执行SQL
            int num = preStatement.executeUpdate();
            if (num > 0) {
                System.out.println("删除成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            JDBCUtil.release(preStatement, connection);
        }
    }

    @Test
    /**
     * 查询数据
     */
    public void query() {
        Connection connection = null;
        PreparedStatement preStatement = null;
        ResultSet res = null;
        try {
            // 注册驱动，获取连接
            connection = JDBCUtil.getConnection();
            // 编写SQL
            String sql = "select * from user";
            // 预处理SQL
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
            // 释放资源
            JDBCUtil.release(res, preStatement, connection);
        }
    }
}
