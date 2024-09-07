package com.myapp.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	@RequestMapping("/api/health")
	public String getApiHealth() {
		return "API Health is good";
	}

	@RequestMapping("/api/hello")
	public String sayHello() {
		return "Hello World!";
	}

}
