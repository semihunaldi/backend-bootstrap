package com.semihunaldi.backendbootstrap.authserver.config.jwt.controller;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by semihunaldi on 29.11.2018
 */
public class BaseRestController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected ModelMapper modelMapper;
}
