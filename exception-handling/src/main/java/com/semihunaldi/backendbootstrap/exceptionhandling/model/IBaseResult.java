package com.semihunaldi.backendbootstrap.exceptionhandling.model;

/**
 * Created by semihunaldi on 29.11.2018
 */
public interface IBaseResult {

	ErrorMessage getErrorMessage();

	Integer getErrorCode();

	String getErrorDescription();
}
