package com.sample.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.sample.listeners.JobResultListener;
import com.sample.listeners.StepTwoListener;
import com.sample.tasks.StepThree;
import com.sample.tasks.StepTwo;
import com.sample.tasks.StepOne;

@Configuration
@EnableBatchProcessing
public class TaskletsConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@PostConstruct
	public void init() {
		System.out.println("Spring Batch Application for Tasklets is Started");
	}

	@Bean
	public Step stepOne() {
		return stepBuilderFactory.get("stepOne").tasklet(new StepOne()).build();
	}

	@Bean
	public Step stepTwo() {
		return stepBuilderFactory.get("stepTwo").tasklet(new StepTwo()).listener(new StepTwoListener()).build();
	}

	@Bean
	public Step stepThree() {
		return stepBuilderFactory.get("stepThree").tasklet(new StepThree()).build();
	}

	@Bean
	public Flow filesFlow() {
		FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("filesFlow");
		flowBuilder.start(stepTwo()).on("FAILED").end().from(stepTwo()).on("COMPLETED").to(stepThree()).end();
		return flowBuilder.build();
	}

	// Notice they have different ids (you can omit the @Bean annotation and Spring
	// will use the method name) but are using the same @Qualifier value.
	// Job Bean with is being called from main method
	@Bean(name = "taskletJob")
	@Qualifier("job")
	@Order(1)
	public Job taskletJob() {
		return this.jobBuilderFactory.get("taskletJob").start(stepOne()).listener(new JobResultListener()).on("FAILED")
				.end().from(stepOne()).on("COMPLETED").to(filesFlow()).end().build();
	}

	@PreDestroy
	public void destroy() {
		System.out.println("Spring Batch Application for Tasklets is Ended");
	}

}
