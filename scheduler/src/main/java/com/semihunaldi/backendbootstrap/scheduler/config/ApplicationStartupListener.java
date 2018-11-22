package com.semihunaldi.backendbootstrap.scheduler.config;

import com.semihunaldi.backendbootstrap.scheduler.jobs.SampleQuartzJob;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * Created by semihunaldi on 22.11.2018
 */

@Component
public class ApplicationStartupListener implements ApplicationListener<ContextRefreshedEvent> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Scheduler scheduler;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("test", "test value");
		JobDetail jobDetail = JobBuilder.newJob(SampleQuartzJob.class)
				.withIdentity(UUID.randomUUID().toString(), "sample-jobs")
				.withDescription("Sample Job 1")
				.usingJobData(jobDataMap)
				.storeDurably()
				.build();
		SimpleTrigger trigger = TriggerBuilder.newTrigger()
				.forJob(jobDetail)
				.withIdentity(jobDetail.getKey().getName(), "sample-triggers")
				.withDescription("Sample Trigger 1")
				.startAt(new Date())
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
				.build();
		try{
			scheduler.scheduleJob(jobDetail,trigger);
		} catch(SchedulerException e){
			logger.error("scheduler failed",e);
		}
	}
}
