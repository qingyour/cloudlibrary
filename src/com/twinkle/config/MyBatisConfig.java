package com.twinkle.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.github.pagehelper.PageInterceptor;
/*
@Configuration定义配置类，一般和@Bean注解联合使用，
@Configuration注解主要标注在某个类上，相当于xml配置文件中的<beans>
@Bean注解主要标注在某个方法上，相当于xml配置文件中的<bean> 
*/
public class MyBatisConfig{
	
	
	 /**配置PageInterceptor分页插件*/
	@Bean
	public PageInterceptor getPageInterceptor(){
		
		PageInterceptor page  = new PageInterceptor();
		Properties properties = new Properties(); 
		properties.setProperty("value", "true");
		page.setProperties(properties);
		return page;
	}
	
	@Bean
	public SqlSessionFactoryBean getSqlSessionFactoryBean(@Autowired DataSource dataSource,
			@Autowired PageInterceptor pageIntercptor){
		SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
		//等同于<property name="dataSource" ref="dataSource"/>
		ssfb.setDataSource(dataSource);
		Interceptor[] plugins={pageIntercptor};
		ssfb.setPlugins(plugins);
		return ssfb;
	}
	
	
	@Bean
	public MapperScannerConfigurer getMapperScannerConfigurer(){
		MapperScannerConfigurer msc = new MapperScannerConfigurer();
		//等同于<property name="basePackage" value="com.twinkle.dao"/>
		msc.setBasePackage("com.twinkle.mapper");
		return msc;
	}
	
	
}