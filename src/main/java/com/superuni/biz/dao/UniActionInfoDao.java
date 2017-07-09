package com.superuni.biz.dao;

import org.springframework.stereotype.Repository;

@Repository
public class UniActionInfoDao extends CommonDao {
	
	private static final String INSERT_SQL = "INSERT INTO UNI_PRIMARY.UNI_ACTION_INFO("
			+ "ACTION_NAME) VALUES (?);";	
	
	public int insert(String actionName) {
		return getJdbcTemplate().update(INSERT_SQL, actionName);
	}
}
