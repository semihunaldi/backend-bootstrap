package com.semihunaldi.backendbootstrap.uithymeleaf.controller;

import com.semihunaldi.backendbootstrap.uithymeleaf.model.LoginModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by semihunaldi on 6.12.2018
 */

@Controller
@Scope("request")
public class LoginController extends BaseController {

	@GetMapping(value = {"/login"})
	public String login(Model model, HttpServletRequest request) {
		model.addAttribute("loginModel", new LoginModel());
		return "pages/login";
	}

	@PostMapping(value = "/loginSubmit")
	public String loginSubmit(@Valid @ModelAttribute("loginModel") LoginModel loginModel, BindingResult bindingResult, Model model, HttpServletRequest request) {
		//TODO login implementation
		if(bindingResult.hasErrors()){
			return "pages/login";
		}
		return "redirect:home";
	}
}
