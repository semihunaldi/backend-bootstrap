package com.semihunaldi.backendbootstrap.uijsf.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;

/**
 * Created by semihunaldi on 23.11.2018
 */

@Component
@ViewScoped
public class IndexController {

	@Getter
	@Setter
	private String test = "Hello World";

	public void buttonListener() {
		System.out.println("Hello World");
	}
}
