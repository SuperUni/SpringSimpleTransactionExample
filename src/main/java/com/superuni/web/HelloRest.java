package com.superuni.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRest {
	@GetMapping("/uni/hello.do")
	public String sayHello() {
		return "Hello Spring";
	}
}
