package com.ict.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class MyController2 {
	@RequestMapping("login.do")
	public ModelAndView logInCommand() {
		return new ModelAndView("login");
	}
	@RequestMapping("admin.do")
	public ModelAndView adminCommand() {
		return new ModelAndView("admin");
	}
	
}
