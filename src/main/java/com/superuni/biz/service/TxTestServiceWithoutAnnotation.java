package com.superuni.biz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.superuni.biz.dao.UniActionLogDao;

@Service
public class TxTestServiceWithoutAnnotation {
	
	@Autowired
	private UniActionLogDao uniActionLogDao;
	@Autowired
	private TxTestWithAnnotationSubService annotationSubService;
	@Autowired
	private TxTestWithoutAnnotationSubService withoutAnnotationSubService;
	
	//-----------------------sub method with annotation--begin---------------------------
	//no @Transactional means each dml will get connection resource, commit immediately, 
	//return connection resource. no throwable could cause roll back
	public Object rollBackDeclaredTx(String action) {
		Object res;
		try {
			//the sub method which annotated with @Transactionl will create new tx.
			res = annotationSubService.throwRunTimeExceptionInSameTx(action);
			uniActionLogDao.insert(res);
			return res;
		} catch (Exception e) {
			res = "F";
			uniActionLogDao.insert(res);//no tx, commit here immediately, can not cause roll back
			throw e;
		}
	}
	
	public Object rollBackDeclaredTx2(String action) {
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
	
	public Object rollBackDeclaredTx3(String action) {
		Object res;
		try {
			res = annotationSubService.throwRunTimeExceptionInSameTx(action);
		} catch (Exception e) {
			res = "F";
		}
		uniActionLogDao.insert(res);
		return res;
	}
	
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
	
	
	public Object commitAllWithException(String action) throws Exception {
		Object res;
		try {
			//commit in new tx
			res = annotationSubService.throwCheckedExceptionInSameTx(action);
			uniActionLogDao.insert(res);
			return res;
		} catch (Exception e) {
			System.out.println("NICO!");
			res = "F";
			uniActionLogDao.insert(res);//commit
			throw e;
		}
	}
	//-------------------------sub method with annotation--end---------------------------
	
	//-----------------------sub method with annotation--begin---------------------------
	public Object commitAllDueToNoneTxInParentAndChildMethod(String action) {
		Object res;
		try {
			res = withoutAnnotationSubService.throwRunTimeExceptionInSameTx(action);
			uniActionLogDao.insert(res);
			return res;
		} catch (Exception e) {
			res = "F";
			uniActionLogDao.insert(res);
			throw e;
		}
	}
	
	public Object commitAllDueToNoneTxInParentAndChildMethod2(String action) throws Exception {
		Object res;
		try {
			res = withoutAnnotationSubService.throwCheckedExceptionInSameTx(action);
			uniActionLogDao.insert(res);
			return res;
		} catch (Exception e) {
			res = "F";
			uniActionLogDao.insert(res);
			throw e;
		}
	}
	//-----------------------sub method with annotation--end-----------------------------
	
}
