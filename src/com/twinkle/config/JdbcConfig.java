package com.twinkle.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:db.properties")
public class JdbcConfig {
	@Value("${jdbc.driver}")
	private String driver;
	
	@Value("${jdbc.url}")
	private String url;
	
	@Value("${jdbc.username}")
	private String username;
	
	@Value("${jdbc.password}")
	private String password;
	
	@Value("${jdbc.maxTotal}")
	private int maxTotal;

	@Value("${jdbc.maxIdle}")
	private int maxIdle;
	
	@Value("${jdbc.initialSize}")
	private int initialSize;
	
	@Bean("dataSource")
	public DataSource getDatasource(){
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setMaxTotal(maxTotal);
		ds.setMaxIdle(maxIdle);
		ds.setInitialSize(initialSize);
		
		return ds;
		
	}
	
}
