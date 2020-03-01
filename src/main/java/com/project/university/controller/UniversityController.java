package com.project.university.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UniversityController {

	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("message", "Hello Spring MVC 5!");
		return "index";
	}
	
	@GetMapping("/serverTime")
	public String date(Model model) {
		model.addAttribute("time", new Date());
		return "serverTime";
	}
}

