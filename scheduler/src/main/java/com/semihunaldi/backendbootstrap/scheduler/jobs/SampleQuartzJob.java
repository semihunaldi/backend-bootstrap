package com.semihunaldi.backendbootstrap.scheduler.jobs;

import com.semihunaldi.backendbootstrap.services.user.UserService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * Created by semihunaldi on 22.11.2018
 */

@Component
public class SampleQuartzJob extends QuartzJobBean {

	@Autowired
	private UserService userService;

	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		System.out.println(jobExecutionContext.getJobDetail().getKey());
		userService.findUserByEmail("test@test.com");
	}
}
