package com.sample.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sample.scheduletasks.QuartzStepOne;
import com.sample.scheduletasks.QuartzStepTwo;

@Configuration
@EnableBatchProcessing
public class ScheduleTaskletsConfig {
	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory steps;

	@Bean
	public Step quartzStepOne() {
		return steps.get("quartzStepOne").tasklet(new QuartzStepOne()).build();
	}

	@Bean
	public Step quartzStepTwo() {
		return steps.get("quartzStepTwo").tasklet(new QuartzStepTwo()).build();
	}

	// we can define any number of jobs with different combinations
	@Bean(name = "quartzJob")
	public Job quartzJob() {
		return jobs.get("quartzJob").start(quartzStepOne()).next(quartzStepTwo()).build();
	}
}
