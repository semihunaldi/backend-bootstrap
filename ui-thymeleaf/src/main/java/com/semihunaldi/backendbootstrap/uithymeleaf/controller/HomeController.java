package com.semihunaldi.backendbootstrap.uithymeleaf.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by semih on 11.09.2016.
 */

@Controller
@Scope(value = "request")
public class HomeController extends BaseController {

	@RequestMapping(value = {"/", "/home"})
	public String home(Model model, HttpServletRequest request) {
		return "home";
	}

	@RequestMapping(value = "/home/generateError")
	public void generateError(Model model, HttpServletRequest request) {
		logger.error("Dummy Error");
		throw new RuntimeException("Dummy Error");
	}

	@RequestMapping(value = "/home/generateForbiddenError")
	public void generateForbiddenError(Model model, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.FORBIDDEN.value());
	}

	@RequestMapping(value = "/home/generateUnauthorizedError")
	public void generateUnauthorizedError(Model model, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.UNAUTHORIZED.value());
	}

	@RequestMapping(value = "/home/generateServiceUnavailableError")
	public void generateServiceUnavailableError(Model model, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.SERVICE_UNAVAILABLE.value());
	}
}
