package com.semihunaldi.backendbootstrap.uithymeleaf.controller;

import com.semihunaldi.backendbootstrap.uithymeleaf.model.FormModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by semihunaldi on 5.12.2018
 */

@Controller
@Scope(value = "request")
public class FormSubmitController extends BaseController {

	@RequestMapping(value = "/formSubmit", method = RequestMethod.GET)
	public String formSubmit(Model model, HttpServletRequest request) {
		model.addAttribute("formModel",new FormModel());
		return "pages/formSubmit";
	}

	@RequestMapping(value = "/formSubmit", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("formModel") FormModel formModel, Model model, HttpServletRequest request) {
		model.addAttribute("formModel", formModel);
		return "pages/formSubmitResult";
	}

	@ModelAttribute("multiCheckboxAllValues")
	public String[] getMultiCheckboxAllValues() {
		return new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	}

	@ModelAttribute("singleSelectAllValues")
	public String[] getSingleSelectAllValues() {
		return new String[]{"YES", "NO", "MAYBE"};
	}
}
