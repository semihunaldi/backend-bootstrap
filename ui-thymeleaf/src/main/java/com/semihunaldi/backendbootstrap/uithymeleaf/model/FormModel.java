package com.semihunaldi.backendbootstrap.uithymeleaf.model;

import lombok.Data;

/**
 * Created by semihunaldi on 5.12.2018
 */

@Data
public class FormModel {
	private String textField;

	private String textareaField;

	private String datetimeField;

	private String colorField;

	private boolean singleCheckboxField;

	private String[] multiCheckboxSelectedValues;

	private String radioButtonSelectedValue;

	private String dropdownSelectedValue;
}
