package com.exercicio1.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/text")
public class exercicio1Controller {
	@GetMapping
	public String text() {
		return "Eu usei a mentalidade de crescimente e atenção aos detalhes ";
	}

}
