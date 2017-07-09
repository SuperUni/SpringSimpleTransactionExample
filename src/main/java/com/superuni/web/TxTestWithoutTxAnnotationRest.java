package com.superuni.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.superuni.biz.service.TxTestServiceWithoutAnnotation;

@RestController
public class TxTestWithoutTxAnnotationRest {
	
	@Autowired
	private TxTestServiceWithoutAnnotation service;
	
	@GetMapping("/uni2/tx1.do")//log "F" exist
	public Object tx1() throws Exception {
		return service.rollBackDeclaredTx("rollBackDeclaredTx");
	}
	
	@GetMapping("/uni2/tx2.do")
	public Object tx2() throws Exception {
		return service.commitAllWithException("commitAllWithException");
	}
	
	@GetMapping("/uni2/tx3.do")
	public Object tx3() throws Exception {
		return service.rollBackActionInfoOnlyWithDefferentTransaction(
				"rollBackActionInfoOnlyWithDefferentTransaction");
	}
	
	@GetMapping("/uni2/tx4.do")//log "F" exist
	public Object tx4() throws Exception {
		return service.rollBackDeclaredTx2("rollBackDeclaredTx2");
	}
	
	@GetMapping("/uni2/tx5.do")//log "F" exist
	public Object tx5() throws Exception {
		return service.rollBackDeclaredTx3("rollBackDeclaredTx3");
	}
	
	@GetMapping("/uni2/tx6.do")//commit all
	public Object tx6() throws Exception {
		return service.commitAllDueToNoneTxInParentAndChildMethod(
				"commitAllDueToNoneTxInParentAndChildMethod");
	}
	
	@GetMapping("/uni2/tx7.do")
	public Object tx7() throws Exception {
		return service.commitAllDueToNoneTxInParentAndChildMethod2(
				"commitAllDueToNoneTxInParentAndChildMethod2");
	}
}
