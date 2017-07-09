package com.superuni.biz.dao;

import org.springframework.stereotype.Repository;

@Repository
public class UniActionLogDao extends CommonDao {
	
	private static final String INSERT_SQL = "INSERT INTO UNI_PRIMARY.UNI_ACTION_LOG("
			+ "IS_ACTION_SUCCESS) VALUES (?);";	
	
	public int insert(Object insRes) {
		return getJdbcTemplate().update(INSERT_SQL, insRes);
	}
}
