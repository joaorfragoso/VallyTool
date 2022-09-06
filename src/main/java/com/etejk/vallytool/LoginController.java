package com.etejk.vallytool;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	@RequestMapping("/main")
	public String main() {
		return "login";
	}
}
