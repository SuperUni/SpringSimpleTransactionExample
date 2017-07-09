package com.superuni.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.superuni.biz.service.TxTestServiceWithAnnotation;

@RestController
public class TxTestWithTxAnnotationRest {
	
	@Autowired
	private TxTestServiceWithAnnotation service;
	
	@GetMapping("/uni/tx1.do")
	public Object tx1() throws Exception {
		return service.rollBackAllWithSameTx("rollBackAllWithSameTx");
	}
	
	@GetMapping("/uni/tx2.do")
	public Object tx2() throws Exception {
		return service.commitAllWithException("commitAllWithException");
	}
	
	@GetMapping("/uni/tx3.do")
	public Object tx3() throws Exception {
		return service.rollBackActionInfoOnlyWithDefferentTransaction(
				"rollBackActionInfoOnlyWithDefferentTransaction");
	}
	
	@GetMapping("/uni/tx4.do")
	public Object tx4() throws Exception {
		return service.rollBackAllWithDifferentTx("rollBackAllWithDifferentTx");
	}
	
	@GetMapping("/uni/tx5.do")
	public Object tx5() throws Exception {
		return service.rollBackAllWithSameTx2("rollBackAllWithSameTx2");
	}
	
	@GetMapping("/uni/tx6.do")
	public Object tx6() throws Exception {
		return service.invalidTxInPublicMethod("validTxInPrivateMethod");
	}
	
	@GetMapping("/uni/tx7.do")
	public Object tx7() throws Exception {
		return service.invalidTxInPrivateMethod("invalidTxInPrivateMethod");
	}
	
	@GetMapping("/uni/tx8.do")
	public Object tx8() throws Exception {
		return service.commitAllWithDifferentTx("commitAllWithDifferentTx");
	}
}
