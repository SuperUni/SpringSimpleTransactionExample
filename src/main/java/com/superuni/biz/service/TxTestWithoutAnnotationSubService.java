package com.superuni.biz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.superuni.biz.dao.UniActionInfoDao;

@Service
public class TxTestWithoutAnnotationSubService {
	
	@Autowired
	private UniActionInfoDao uniActionInfoDao;
	
	public Object throwRunTimeExceptionInSameTx(String action) {
		uniActionInfoDao.insert(action);//no tx, commit here immediately, can not cause roll back
		throw new RuntimeException("rollBackActionInfoOnly");
	}
	
	public Object throwCheckedExceptionInSameTx(String action) throws Exception {
		uniActionInfoDao.insert(action);//no tx, commit here immediately, can not cause roll back
		throw new Exception("commitAllWithException");
	}
}
