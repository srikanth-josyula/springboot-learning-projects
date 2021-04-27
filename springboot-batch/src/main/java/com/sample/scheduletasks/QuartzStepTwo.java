package com.sample.scheduletasks;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class QuartzStepTwo implements Tasklet {
	
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.out.println("Execute StepTwo from Quartz Job Start");

		// some logic
		
		System.out.println("Execute StepTwo from Quartz Job End");
		return RepeatStatus.FINISHED;
	}

}