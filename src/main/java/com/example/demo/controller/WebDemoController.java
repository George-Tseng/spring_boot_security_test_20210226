package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebDemoController {
	
	@GetMapping("/")
	public String webDemo() {
		return "Spring Boot with Spring Security basic setting..., login success...";
	}
}
