package com.exercicio2.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/txt")
public class exercicio2Controller {
	@GetMapping
	public String txtShow() {
	return "Quero desenvolver mais Spring Boot";
	}

}
