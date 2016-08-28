package ru;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@RequestMapping("/")
	@ResponseBody
	public String root() {
		return "root page";
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public String login() {
		return "login";
	}

	@RequestMapping("/info")
	public String info() {
		return "info";
	}
	
}
