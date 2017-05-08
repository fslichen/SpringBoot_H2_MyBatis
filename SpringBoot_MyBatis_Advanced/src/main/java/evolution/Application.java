package evolution;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import evolution.entity.SimpleStudent;
import evolution.entity.Student;
import evolution.mybatis.mapper.AnotherMapper;
import evolution.mybatis.mapper.AnyMapper;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	private AnyMapper anyMapper;

	@Autowired
	private AnotherMapper anotherMapper;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private SqlSession sqlSession;
	
	public void initialize() throws SQLException {
		// Initialize
		Statement statement = dataSource.getConnection().createStatement();
		statement.execute("drop table if exists any_table");
		statement.execute("create table any_table(id int primary key auto_increment"
				+ ", name varchar(20), gender varchar(20), address varchar(40), phone varchar(20))");
		// Insert
		anyMapper.insert("Chen", "M", "Illinois", "217-819-9008");
	}

	public void annotationDemo() {
		// Find by Mapper
		List<Student> students = anyMapper.selectByName("Chen");// The SQL statement is written within @Select. 
		System.out.println(students);
		// Find by External SQL
		Student student = anyMapper.selectById(1);
		System.out.println(student);
		// Use @Results
		List<SimpleStudent> simpleStudents = anyMapper.selectSimpleStudents("Chen");
		System.out.println(simpleStudents);
	}

	public void xmlDemo() {
		// Insert by XML
		Student student = new Student();
		student.setName("Ling");
		anotherMapper.insertUsingXml(student);
		// Find All Using XML
		List<SimpleStudent> simpleStudents = anotherMapper.selectAllUsingXml();
		System.out.println(simpleStudents);
		// Find by Name Using XML
		simpleStudents = anotherMapper.selectByNameUsingXml("Chen");
		System.out.println(simpleStudents);
	}

	public void sqlSessionTemplateDemo() {
		List<Student> students = sqlSession.selectList(AnyMapper.class.getName() + ".selectByName", "Chen");
		System.out.println(students);
	}
	
	@Override
	public void run(String... arg0) throws Exception {
		initialize();
		annotationDemo();
		xmlDemo();
		sqlSessionTemplateDemo();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
