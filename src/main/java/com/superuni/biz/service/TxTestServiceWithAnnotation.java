package com.superuni.biz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.superuni.biz.dao.UniActionLogDao;

@Service
public class TxTestServiceWithAnnotation {
	
	@Autowired
	private UniActionLogDao uniActionLogDao;
	@Autowired
	private TxTestWithAnnotationSubService annotationSubService;
	
	@Transactional
	public Object rollBackAllWithSameTx(String action) {
		Object res;
		try {
			res = annotationSubService.throwRunTimeExceptionInSameTx(action);
			uniActionLogDao.insert(res);
			return res;
		} catch (Exception e) {
			res = "F";
			uniActionLogDao.insert(res);
			throw e;
		}
	}
	
	//new tx will suspend previous tx first, then create new tx and get connection
	//resource, commit or roll back, return connection resource, close new tx.
	@Transactional
	public Object rollBackAllWithDifferentTx(String action) {
		Object res;
		try {
			res = annotationSubService.throwRunTimeExceptionInNewTx(action);
			uniActionLogDao.insert(res);
			return res;
		} catch (Exception e) {
			res = "F";
			uniActionLogDao.insert(res);
			throw e;
		}
	}
	
	//Transaction rolled back because it has been marked as roll back-only
	@Transactional
	public Object rollBackAllWithSameTx2(String action) {
		Object res;
		try {
			res = annotationSubService.throwRunTimeExceptionInSameTx(action);
		} catch (Exception e) {
			res = "F";
		}
		uniActionLogDao.insert(res);
		return res;
	}
	
	@Transactional
	public Object rollBackActionInfoOnlyWithDefferentTransaction(String action) {
		Object res;
		try {
			res = annotationSubService.throwRunTimeExceptionInNewTx(action);
		} catch (Exception e) {
			res = "F";
		}
		uniActionLogDao.insert(res);
		return res;
	}
	
	
	@Transactional
	public Object commitAllWithException(String action) throws Exception {
		Object res;
		try {
			res = annotationSubService.throwCheckedExceptionInSameTx(action);
			uniActionLogDao.insert(res);
			return res;
		} catch (Exception e) {
			System.out.println("NICO!");
			res = "F";
			uniActionLogDao.insert(res);
			throw e;
		}
	}
	
	//only aspectJ can handle different tx in same class both for public 
	//and private method
	//configuration is sth like @EnableTransactionManagement(mode=AdviceMode.ASPECTJ)
	@Transactional//expect both "T" and "F"
	public Object invalidTxInPublicMethod(String action) {
		uniActionLogDao.insert("T");
		try {
			logInfo12();
		} catch (Exception e) {
			//do nothing
			return "F1";
		}
		return "T1";
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void logInfo12() {
		uniActionLogDao.insert("F");
		throw new RuntimeException();
	}
	
	@Transactional//expect both "T" and "F"
	public Object invalidTxInPrivateMethod(String action) {
		uniActionLogDao.insert("T");
		try {
			logInfo22();
		} catch (Exception e) {
			//do nothing
			return "F2";
		}
		return "T2";
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	private void logInfo22() {
		uniActionLogDao.insert("F");
		throw new RuntimeException();
	}
	
	@Transactional
	public Object commitAllWithDifferentTx(String action) {
		Object res;
		try {
			res = annotationSubService.commitlInNewTx(action);
			uniActionLogDao.insert(res);
			return res;
		} catch (Exception e) {
			System.out.println("NICO!");
			res = "F";
			uniActionLogDao.insert(res);
			return res;
		}
	}
}
