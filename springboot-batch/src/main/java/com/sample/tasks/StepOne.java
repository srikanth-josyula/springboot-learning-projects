package com.sample.tasks;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class StepOne implements Tasklet {

	private static final String VALUE = "Input value sent StepOne Tasklet";
	private boolean status = true;

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		
		
		System.out.println("StepOne :: execute :: StepOne start ");

		// Send data to other tasklets
		chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("value",
				VALUE);

		if (status) {
			contribution.setExitStatus(ExitStatus.COMPLETED);
			System.out.println("StepOne :: execute :: StepOne COMPLETED");

		} else {
			contribution.setExitStatus(ExitStatus.FAILED);
			System.out.println("StepOne :: execute :: StepOne FAILED");
			// contribution.setExitStatus(new ExitStatus("MY CUSTOM MESSAGE"));
		}

		return RepeatStatus.FINISHED;
	}
}