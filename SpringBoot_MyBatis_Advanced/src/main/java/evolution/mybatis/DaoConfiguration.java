package evolution.mybatis;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import evolution.util.FileUtil;

@Configuration// Denote the current class as the configuration class. 
// This notation is discouraged because it makes re-factoring harder in that you need to change the path when re-factoring.
@MapperScan(basePackages = "evolution")// Scan all the mappers under the mapper package for safety reasons.
@EnableTransactionManagement
public class DaoConfiguration {
	@Value("${mybatis.typeAliasesPackage}")
	private String typeAliasesPackage;
	
	@Value("${mybatis.xmlMappersPackage}")
	private String xmlMappersPackage;
	
	@Bean// Inject DataSource
	@ConfigurationProperties("datasource")// Reads the properties from application.properties automatically. The prefix is datasource. 
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean// Inject SqlSessionFactoryBean
	public SqlSessionFactoryBean sqlSessionFactoryBean() {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setTypeAliasesPackage(this.typeAliasesPackage);
		sessionFactory.setMapperLocations(FileUtil.getResources(this.xmlMappersPackage));
		return sessionFactory;
	}

	@Bean// Inject DataSourceTransactionManager
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	@Bean// Inject SqlSessionTemplate
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactoryBean().getObject());
	}
}
