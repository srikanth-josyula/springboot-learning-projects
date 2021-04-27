package com.sample.tasks;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class StepTwo implements Tasklet {

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.out.println("StepTwo start..");


		// pull variable from JobExecutionContext
		String valueReceived = (String) chunkContext.getStepContext().getStepExecution().getJobExecution()
				.getExecutionContext().get("value");
		System.out.println(valueReceived);
		// ... your code

		System.out.println("StepTwo done..");
		return RepeatStatus.FINISHED;
	}
}