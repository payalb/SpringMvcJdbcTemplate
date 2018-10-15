package com.java.config;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


public class SpringDbConfig {

	@Autowired DatabasePropertyConfig util;
	@Bean
	public DataSource getDataSource() {
		BasicDataSource ds= new BasicDataSource();
		ds.setUrl(util.getUrl());
		ds.setPassword(util.getPassword());
		ds.setUsername(util.getUsername());
		ds.setDriverClassName(util.getDriverName());
		return ds;
	}
	
	@Bean
	public NamedParameterJdbcTemplate getTemplate() {
		NamedParameterJdbcTemplate  template= new NamedParameterJdbcTemplate(getDataSource());
		return template;
	}
}
