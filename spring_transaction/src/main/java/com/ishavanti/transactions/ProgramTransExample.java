package com.ishavanti.transactions;

import com.ishavanti.bean.Book;
import com.ishavanti.util.TemplateUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgramTransExample {

    // 编程式事务管理：事务模板TransactionTemplate方式实现
    public void addBook(final Book book) {
        // 获取事务模板对象
        TransactionTemplate transactionTemplate = TemplateUtils.getTransactionTemplate();
        // 可设置事务属性，如隔离级别、超时时间等
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try {
                    // 数据库操作
                    // 简单模板化新增数据
                    SimpleJdbcInsert simpleInsert = TemplateUtils.getSimpleJdbcTemplate();
                    simpleInsert.withTableName("books").usingColumns("isbn", "name", "price", "pubdate");
                    Map<String, Object> parameters = new HashMap<String, Object>();
                    parameters.put("isbn", book.getIsbn());
                    parameters.put("name", book.getName());
                    parameters.put("price", book.getPrice());
                    parameters.put("pubdate", book.getPubdate());
                    simpleInsert.execute(parameters);
                    System.out.println("新增数据成功");
                } catch (Exception e) {
                    transactionStatus.setRollbackOnly();
                    e.printStackTrace();
                }
            }
        });
    }

    public Book findBookByIsbn(final String isbn) {
        TransactionTemplate transactionTemplate = TemplateUtils.getTransactionTemplate();
        Book book = null;
        List<Map<String, Object>> books = (List<Map<String, Object>>)transactionTemplate.execute(new TransactionCallback<Object>() {
            public Object doInTransaction(TransactionStatus transactionStatus) {
                JdbcTemplate jdbcTemplate = TemplateUtils.getJdbcTemplate();
                return jdbcTemplate.queryForList("select isbn, name, price, pubdate from books where isbn=?", isbn);
            }
        });
        if (books != null) {
            Map<String, Object> map = books.get(0);
            book = new Book();
            book.setIsbn(map.get("isbn").toString());
            book.setName(map.get("name").toString());
            book.setPrice((Float) map.get("price"));
            book.setPubdate((Date) map.get("pubdate"));
        }
        return book;
    }

    // 编程式事务管理：事务管理器PlatformTransactionManager方式实现
    public void updateBookByIsbn(Book book) {
        // 第一步：获取JDBC事务管理器
        DataSourceTransactionManager dtm = TemplateUtils.getDataSourceTransactionManager();
        // 第二步：创建事务管理器属性对象
        DefaultTransactionDefinition transDef = new DefaultTransactionDefinition();
        // 根据需要，设置事务管理器的相关属性
        // 设置传播行为属性
        transDef.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRED);
        // 第三步：获得事务状态对象
        TransactionStatus ts = dtm.getTransaction(transDef);
        // 第四步：基于当前事务管理器，获取数据源，创建操作数据库的JDBC模板对象
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dtm.getDataSource());
        try {
            // 第五步：业务操作
            jdbcTemplate.update("update books set price=?, name=? where isbn=?", book.getPrice(), book.getName(), book.getIsbn());
            // 第六步：提交事务
            dtm.commit(ts);  // 如果不commit，则更新无效果
        } catch (Exception e) {
            // 出现异常时回滚
            dtm.rollback(ts);
            e.printStackTrace();
        }
    }
}
