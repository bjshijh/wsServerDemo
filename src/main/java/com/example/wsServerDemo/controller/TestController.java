package com.example.wsServerDemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
@Controller
public class TestController {
	@RequestMapping("/test")
	public String test(HttpServletRequest request) {
		return "wsclient.html";
	}
}
