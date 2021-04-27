package com.sample;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableBatchProcessing
@SpringBootApplication
@EnableScheduling // for enabling @Scheduler
public class BatchProcessing implements CommandLineRunner {
	@Autowired
	JobLauncher jobLauncher;

	/**
	 * Autowiring to run the job using spring-batch scheduler
	 **/
	/*@Autowired
	@Qualifier("quartzJob")
	Job quartzJob;*/

	//@Scheduled(cron = "*/5 * * * * *")
	/*public void perform() throws Exception {
		System.out.println("Running the Job using @Scheduler at :" + new Date());
		JobParameters param = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		JobExecution execution = jobLauncher.run(quartzJob, param);
		System.out.println("Running the Job using @Scheduler finished with status :" + execution.getStatus());
	}*/

	/**
	 * Autowiring to run the Chuck and tasklet jobs
	 **/	
	private List<Job> joblist;

	@Autowired
	@Qualifier("chunkJob")
	Job chunkJob;

	@Autowired
	@Qualifier("taskletJob")
	Job taskletJob;
	
	@Autowired
	@Qualifier("job")
	public void setXList(List<Job> joblist) {
		this.joblist = joblist;
	}
	
	/**
	 * Autowiring to run the FlatFile Reader/Writer jobs
	 **/	
	@Autowired
	@Qualifier("flatfileJob")
	Job flatfileJob;
	
	/**
	 * Autowiring to Insert Data into DB
	 **/	
	@Autowired
	@Qualifier("dbInsersionJob")
	Job dbInsersionJob;
	
	@Override
	public void run(String... args) throws Exception {
		JobParameters params = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();

		// jobLauncher.run(taskletJob, params);
		//jobLauncher.run(chunkJob, params);
		// jobLauncher.run(flatfileJob, params);
		 jobLauncher.run(dbInsersionJob, params);

		/*
		 * for(Job job : joblist) { jobLauncher.run(job, params); }
		 */
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BatchProcessing.class, args);
	}

}
