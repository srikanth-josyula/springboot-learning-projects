package com.sample.tasks;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class StepThree implements Tasklet, StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("Before Step2 Execution");
	}

	
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.out.println("StepThree start..");

		// ... your code

		System.out.println("StepThree done..");
		return RepeatStatus.FINISHED;
	}
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		
		System.out.println("After Step2 Execution");
		boolean condition = true;
		
		if (condition) {
			return ExitStatus.COMPLETED;
		} else {
			return ExitStatus.FAILED;
		}

	}
}