package evolution.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import evolution.dao.sql.AnySql;
import evolution.entity.SimpleStudent;
import evolution.entity.Student;

@Mapper// Scanned by @MapperScan in DaoConfiguration.
public interface AnyMapper {
	@Select("SELECT * FROM any_table WHERE NAME = #{name}")
	public List<Student> selectByName(@Param("name") String name);
	
	@Insert("INSERT INTO any_table(name, gender, address, phone) "
			+ "VALUES(#{name}, #{gender}, #{address}, #{phone})")
	public void insert(@Param("name") String name,
			@Param("gender") String gender,
			@Param("address") String address,
			@Param("phone") String phone);
	
	@SelectProvider(type = AnySql.class, method = "selectById")
	public Student selectById(@Param("id") Integer id);
	
	@Results({
		@Result(property = "id", column = "id"),
		@Result(property = "name", column = "name")
	})
	@Select("SELECT * FROM any_table WHERE NAME = #{name}")
	public List<SimpleStudent> selectSimpleStudents(@Param("name") String name);
}
