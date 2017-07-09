package com.superuni.biz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.superuni.biz.dao.UniActionInfoDao;

@Service
public class TxTestWithAnnotationSubService {
	
	@Autowired
	private UniActionInfoDao uniActionInfoDao;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Object throwRunTimeExceptionInNewTx(String action) {
		uniActionInfoDao.insert(action);
		throw new RuntimeException("rollBackActionInfoOnly");
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Object commitlInNewTx(String action) {
		uniActionInfoDao.insert(action);
		return "T";
	}
	
	@Transactional
	public Object throwRunTimeExceptionInSameTx(String action) {
		uniActionInfoDao.insert(action);
		throw new RuntimeException("rollBackActionInfoOnly");
	}
	
	@Transactional
	public Object throwCheckedExceptionInSameTx(String action) throws Exception {
		uniActionInfoDao.insert(action);
		throw new Exception("commitAllWithException");
	}
}
