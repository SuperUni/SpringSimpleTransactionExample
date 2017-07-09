package com.superuni.biz.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class CommonDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
}
