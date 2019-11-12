import com.ishavanti.entity.Student;
import com.ishavanti.entity.StudentRowMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Test {
    private JdbcTemplate jdbcTemplate;

    {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
    }

    @org.junit.Test
    public void testExecute() {
        jdbcTemplate.execute("create table user1(id int, name varchar(20))");
    }

    @org.junit.Test
    public void testUpdate() {
        String sql = "insert into student(name, sex) values(?, ?)";
        jdbcTemplate.update(sql, new Object[]{"张三", "男"});
    }

    @org.junit.Test
    public void testUpdate2() {
        String sql = "update student set sex=? where id=?";
        jdbcTemplate.update(sql, "女", 1);
    }

    @org.junit.Test
    public void testBatchUpdate() {
        String[] sqls = {
                "insert into student(name, sex) values('李四', '男')",
                "insert into student(name, sex) values('王五', '女')",
                "update student set sex='男' where id=3",
        };
        jdbcTemplate.batchUpdate(sqls);
    }

    @org.junit.Test
    public void testBatchUpdate2() {
        String sql = "insert into selection(student, course) values(?, ?)";
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[]{1, 1001});
        list.add(new Object[]{1, 1003});
        jdbcTemplate.batchUpdate(sql, list);
    }

    @org.junit.Test
    public void testQuerySimple1() {
        String sql = "select count(*) from student";
        int count = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(count);
    }

    @org.junit.Test
    public void testQuerySimple2() {
        String sql = "select name from student where sex=?";
        List<String> list = jdbcTemplate.queryForList(sql, String.class, "男");
        System.out.println(list);
    }

    @org.junit.Test
    public void testQueryMap1() {
        String sql = "select * from student where id=?";
        Map<String, Object> stu = jdbcTemplate.queryForMap(sql, 1);
        System.out.println(stu);
    }

    @org.junit.Test
    public void testQueryMap2() {
        String sql = "select * from student";
        List<Map<String, Object>> students = jdbcTemplate.queryForList(sql);
        System.out.println(students);
    }

    @org.junit.Test
    public void testQueryEntity1() {
        String sql = "select * from student where id=?";
        Student stu = jdbcTemplate.queryForObject(sql, new StudentRowMapper(), 1);
        System.out.println(stu);
    }

    @org.junit.Test
    public void testQueryEntity2() {
        String sql = "select * from student";
        List<Student> students = jdbcTemplate.query(sql, new StudentRowMapper());
        System.out.println(students);
    }
}
