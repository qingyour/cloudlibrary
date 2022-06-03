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
@Configuration���������࣬һ���@Beanע������ʹ�ã�
@Configurationע����Ҫ��ע��ĳ�����ϣ��൱��xml�����ļ��е�<beans>
@Beanע����Ҫ��ע��ĳ�������ϣ��൱��xml�����ļ��е�<bean> 
*/
public class MyBatisConfig{
	
	
	 /**����PageInterceptor��ҳ���*/
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
		//��ͬ��<property name="dataSource" ref="dataSource"/>
		ssfb.setDataSource(dataSource);
		Interceptor[] plugins={pageIntercptor};
		ssfb.setPlugins(plugins);
		return ssfb;
	}
	
	
	@Bean
	public MapperScannerConfigurer getMapperScannerConfigurer(){
		MapperScannerConfigurer msc = new MapperScannerConfigurer();
		//��ͬ��<property name="basePackage" value="com.twinkle.dao"/>
		msc.setBasePackage("com.twinkle.mapper");
		return msc;
	}
	
	
}