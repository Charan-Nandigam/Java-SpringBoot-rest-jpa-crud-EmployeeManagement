package com.cg.springbootrestjpacrud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/")
	public String home() {
		return "<h1>Welcome to Spring restful web services</h1>";
	}
}
