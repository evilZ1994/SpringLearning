package ishavanti.jdbc.demo1;

import ishavanti.jdbc.util.JDBCUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * SQL注入示例
 */
public class JDBCDemo3 {

    @Test
    /**
     * 测试SQL注入
     */
    public void demo1() {
        // SQL语句为select * from user where username='aaa' or '1' and password='s4d4e4wds'
        // 条件判断优先级为 (username='aaa') or ('1' and password='s4d4e4wds') 所以username='aaa'为true，整个条件判断都为true
        boolean flag = login("aaa' or '1", "s4d4e4wds");

        // -- 符号在SQL语法中为注释，那么整个条件判断只剩 username='aaa'，所以就为true
        boolean flag2 = login("aaa' -- ", "d4jls5jdo2fs4");

        if (flag) {
            System.out.println("登录成功！");
        } else {
            System.out.println("登录失败！");
        }
    }

    /**
     * 登录功能
     * 产生SQL注入漏洞的方法
     */
    public static boolean login(String username, String password) {
        Connection connection = null;
        Statement statement = null;
        ResultSet res = null;
        boolean flag = false;
        try {
            // 注册驱动，获取连接
            connection = JDBCUtil.getConnection();
            // 创建Statement对象
            statement = connection.createStatement();
            // 编写SQL
            String sql = "select * from user where username='" + username + "' and password='" + password + "'";
            System.out.println(sql);
            // 执行SQL
            res = statement.executeQuery(sql);
            if (res.next()) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            JDBCUtil.release(res, statement, connection);
        }

        return flag;
    }

    /**
     * 可以避免SQL注入的登录功能
     */
    public static boolean login2(String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet res = null;
        try {
            // 注册驱动，获取连接
            connection = JDBCUtil.getConnection();
            // 编写SQL
            String sql = "select * from user where username=? and password=?";
            // 预处理SQL语句
            preparedStatement = connection.prepareStatement(sql);
            // 设置参数（参数index从1开始）
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            // 执行SQL
            res = preparedStatement.executeQuery();
            if (res.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            JDBCUtil.release(res, preparedStatement, connection);
        }
        return false;
    }
}
