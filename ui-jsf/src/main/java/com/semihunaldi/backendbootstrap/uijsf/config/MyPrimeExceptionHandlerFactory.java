package com.semihunaldi.backendbootstrap.uijsf.config;

import org.primefaces.application.exceptionhandler.PrimeExceptionHandlerFactory;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 * Created by semihunaldi on 26.11.2018
 */

public class MyPrimeExceptionHandlerFactory extends PrimeExceptionHandlerFactory {

	public MyPrimeExceptionHandlerFactory(ExceptionHandlerFactory wrapped) {
		super(wrapped);
	}

	@Override
	public ExceptionHandler getExceptionHandler() {
		return new MyPrimeExceptionHandler(super.getWrapped().getExceptionHandler());
	}

	@Override
	public ExceptionHandlerFactory getWrapped() {
		return super.getWrapped();
	}
}