package com.sample.config;

import javax.sql.DataSource;

import org.h2.server.web.WebServlet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.sample.model.FlatFileModel;
import com.sample.utils.DBLogProcessor;

@Configuration
@EnableBatchProcessing
public class DBChucksConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean(name = "dbInsersionJob")
	public Job dbInsersionJob() {
		return jobBuilderFactory.get("dbInsersionJob").incrementer(new RunIdIncrementer()).start(dbInsersionStep())
				.build();
	}

	@Bean
	public Step dbInsersionStep() {
		return stepBuilderFactory.get("dbInsersionStep").<FlatFileModel, FlatFileModel>chunk(5).reader(dbreader())
				.processor(dbprocessor()).writer(dbwriter()).build();
	}

	@Bean
	public ItemProcessor<FlatFileModel, FlatFileModel> dbprocessor() {
		return new DBLogProcessor();
	}

	@Bean
	public FlatFileItemReader<FlatFileModel> dbreader() {
		FlatFileItemReader<FlatFileModel> itemReader = new FlatFileItemReader<FlatFileModel>();
		itemReader.setLineMapper(lineMapper());
		itemReader.setLinesToSkip(1);
		itemReader.setResource(new ClassPathResource("FlatFileInputData.csv"));
		return itemReader;
	}

	@Bean
	public LineMapper<FlatFileModel> lineMapper() {
		DefaultLineMapper<FlatFileModel> lineMapper = new DefaultLineMapper<FlatFileModel>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[] { "key", "value", "condition" });
		lineTokenizer.setIncludedFields(new int[] { 0, 1, 2 });
		BeanWrapperFieldSetMapper<FlatFileModel> fieldSetMapper = new BeanWrapperFieldSetMapper<FlatFileModel>();
		fieldSetMapper.setTargetType(FlatFileModel.class);
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;
	}

	@Bean
	public JdbcBatchItemWriter<FlatFileModel> dbwriter() {
		JdbcBatchItemWriter<FlatFileModel> itemWriter = new JdbcBatchItemWriter<FlatFileModel>();
		itemWriter.setDataSource(dataSource());
		itemWriter.setSql("INSERT INTO FLATFILEMODEL (KEY, VALUE, CONDITION) VALUES (:key, :value, :condition)");
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<FlatFileModel>());
		return itemWriter;
	}

	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
		return embeddedDatabaseBuilder.addScript("classpath:org/springframework/batch/core/schema-drop-h2.sql")
				.addScript("classpath:org/springframework/batch/core/schema-h2.sql")
				.addScript("classpath:flatFileModel.sql").setType(EmbeddedDatabaseType.H2).build();
	}

	@Bean
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
		registration.addUrlMappings("/console/*");
		return registration;
	}

}