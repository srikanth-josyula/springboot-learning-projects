package com.sample.listeners;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class StepTwoListener implements StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("Before Step Execution");
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		
		System.out.println("After Step Execution");
		boolean condition = true;
		
		if (condition) {
			return ExitStatus.COMPLETED;
		} else {
			return ExitStatus.FAILED;
		}

	}
}
