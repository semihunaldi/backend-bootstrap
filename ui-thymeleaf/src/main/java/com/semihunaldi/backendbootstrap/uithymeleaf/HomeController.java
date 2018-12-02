package com.semihunaldi.backendbootstrap.uithymeleaf;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by semih on 11.09.2016.
 */

@Controller
@Scope(value = "request")
public class HomeController {

	@RequestMapping(value = {"/", "/home"})
	public String home(Model model, HttpServletRequest request) {
		return "home";
	}
}
