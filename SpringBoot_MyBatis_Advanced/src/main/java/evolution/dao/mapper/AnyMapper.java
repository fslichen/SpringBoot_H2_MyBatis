package evolution.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import evolution.dao.sql.AnySql;
import evolution.entity.AnyEntity;

@Mapper// Scanned by @MapperScan in DaoConfiguration.
public interface AnyMapper {
	@Select("SELECT * FROM any_table WHERE NAME = #{name}")
	public List<AnyEntity> selectByName(@Param("name") String name);
	
	@Insert("INSERT INTO any_table(name) VALUES(#{name})")
	public void insert(@Param("name") String name);
	
	@SelectProvider(type = AnySql.class, method = "selectById")
	public AnyEntity selectById(@Param("id") Integer id);
}
