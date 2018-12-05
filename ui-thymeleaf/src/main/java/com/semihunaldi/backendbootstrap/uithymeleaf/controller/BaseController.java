package com.semihunaldi.backendbootstrap.uithymeleaf.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.Serializable;

/**
 * Created by semihunaldi on 5.12.2018
 */
public class BaseController implements Serializable {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Environment env;

	@ModelAttribute("appName")
	public String appName() {
		return env.getProperty("spring.application.name");
	}
}
