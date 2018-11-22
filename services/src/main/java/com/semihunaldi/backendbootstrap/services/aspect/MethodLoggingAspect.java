package com.semihunaldi.backendbootstrap.services.aspect;

import com.google.common.base.Stopwatch;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.TimeUnit;

/**
 * Created by semihunaldi on 22.11.2018
 */

@Configuration
@Aspect
public class MethodLoggingAspect {
	private final Logger logger = LoggerFactory.getLogger(MethodLoggingAspect.class);

	@Around("(within(com.semihunaldi.backendbootstrap.services.BaseServiceImpl+)) " +
			        "&& !@annotation(com.semihunaldi.backendbootstrap.services.aspect.DisableLogging) " +
			        "&& !@target(com.semihunaldi.backendbootstrap.services.aspect.DisableLogging) ")
	public Object logMethodCall(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable
	{
		return logMethod(proceedingJoinPoint);
	}

	private Object logMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
	{
		MethodCallLog methodCallLog = buildMethodCallLog(proceedingJoinPoint);
		Stopwatch stopwatch = Stopwatch.createUnstarted();
		stopwatch.start();
		try
		{
			Object returnValue = proceedingJoinPoint.proceed();
			methodCallLog.setDuration(String.valueOf(stopwatch.elapsed(TimeUnit.MILLISECONDS)));
			logger.debug("{}", methodCallLog.toString());
			return returnValue;
		}
		catch (Exception exception)
		{
			methodCallLog.setMessage(convertStackTraceToString(exception, 4000));
			methodCallLog.setDuration(String.valueOf(stopwatch.elapsed(TimeUnit.MILLISECONDS)));
			logger.error("{}", methodCallLog);
			throw exception;
		}
	}

	private String convertStackTraceToString(Throwable exception)
	{
		if (exception == null)
		{
			throw new IllegalArgumentException("Stack trace conversion failed");
		}
		StringWriter sw = new StringWriter();
		exception.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}


	private String convertStackTraceToString(Throwable exception, int length)
	{
		return StringUtils.substring(convertStackTraceToString(exception), 0, length);
	}


	private MethodCallLog buildMethodCallLog(ProceedingJoinPoint proceedingJoinPoint)
	{
		MethodCallLog methodCallLog = new MethodCallLog();
		methodCallLog.setMethodName(proceedingJoinPoint.getTarget().getClass() + "." + proceedingJoinPoint.getSignature().getName());
		return methodCallLog;
	}
}
