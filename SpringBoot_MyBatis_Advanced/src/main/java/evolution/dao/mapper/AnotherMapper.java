package evolution.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import evolution.entity.SimpleStudent;
import evolution.entity.Student;

@Mapper
public interface AnotherMapper {
	// Instead of using Java annotations, you are storing SQL statements inside anotherMapper.xml.
	public List<SimpleStudent> selectAllUsingXml();
	public List<SimpleStudent> selectByNameUsingXml(@Param("name") String name);
	public void insertUsingXml(Student student);
}
