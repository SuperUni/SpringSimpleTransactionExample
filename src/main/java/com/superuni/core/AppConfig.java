package com.superuni.core;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@ComponentScan({"com.superuni.biz"})
@EnableTransactionManagement
@PropertySource({"core.properties"})
@Import({WebConfig.class})
public class AppConfig {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${mysql.jdbc.url}")
	private String jdbcUrl;
	@Value("${mysql.jdbc.username}")
	private String jdbcUserName;
	@Value("${mysql.jdbc.pwd}")
	private String jdbcPwd;
	
	@Bean
	public DataSource getDataSource() {
		ComboPooledDataSource  ds = new ComboPooledDataSource();
		ds.setJdbcUrl(jdbcUrl);
		ds.setUser(jdbcUserName);
		ds.setPassword(jdbcPwd);
		logger.info("load data source complete");
		return ds;
	}
	
	@Bean
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(getDataSource());
	}
}
