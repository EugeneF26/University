package com.project.university.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UniversityController {
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
	
	@GetMapping("/")
	public String sayHello1(Model model) throws Exception {
		model.addAttribute("serverTime", "8 часов");
		return "hello_world";
	}
}

