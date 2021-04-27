package com.sample.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.sample.model.FlatFileModel;
import com.sample.processor.ConsoleItemWriter;
import com.sample.utils.ItemCountItemStream;

@Configuration
@EnableBatchProcessing
public class FlatFileBatchConfig {

	private Resource inputResources = new ClassPathResource("FlatFileInputData.csv");

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	// define jobs for reader/writer
	@Bean(name = "flatfileJob")
	public Job flatfileReaderJob() {
		return jobBuilderFactory.get("flatfileJob").incrementer(new RunIdIncrementer())
				.start(flatfileStep()).build();
	}
	
	// define steps for reader/writer
	@Bean
	public Step flatfileStep() {
		return stepBuilderFactory.get("flatfileStep").<FlatFileModel, FlatFileModel>chunk(5).reader(filereader())
				.writer(filewriter()).stream(stream()).build();
	
		// to print on console
		//return stepBuilderFactory.get("flatfileReaderstep").<FlatFileModel, FlatFileModel>chunk(5).reader(filereader()).writer(consolewriter()).build();
		
		// to read multiple files
		//return stepBuilderFactory.get("flatfileWriterstep").<FlatFileModel,FlatFileModel>chunk(5).reader(multiResourceItemReader()).writer(writer()).build();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FlatFileItemReader<FlatFileModel> filereader() {
		FlatFileItemReader<FlatFileModel> reader = new FlatFileItemReader<FlatFileModel>();
		reader.setResource(inputResources);
		reader.setLinesToSkip(1);
		reader.setLineMapper(new DefaultLineMapper() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "key", "value", "condition" });
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<FlatFileModel>() {
					{
						setTargetType(FlatFileModel.class);
					}
				});
			}
		});
		return reader;
	}

	@Bean
	public FlatFileItemWriter<FlatFileModel> filewriter() {
		FlatFileItemWriter<FlatFileModel> writer = new FlatFileItemWriter<>();
		writer.setResource(new FileSystemResource("csv/FlatFileOutputData.csv"));
		writer.setAppendAllowed(true);
		writer.setLineAggregator(new DelimitedLineAggregator<FlatFileModel>() {
			{
				setDelimiter(",");
				setFieldExtractor(new BeanWrapperFieldExtractor<FlatFileModel>() {
					{
						setNames(new String[] { "key", "value", "condition" });
					}
				});
			}
		});
		return writer;
	}

	@Bean
	public ConsoleItemWriter<FlatFileModel> consolewriter() {
		return new ConsoleItemWriter<FlatFileModel>();
	}
	
	/**
	 * Using ItemStream to count the items 
	 **/
	
	@Bean
	public ItemCountItemStream stream() {
	    return new ItemCountItemStream();
	}
	
	/**
	 *Read CSV files with MultiResourceItemReader 
	 **/

	//@Value("input/inputData*.csv")
	private Resource[] resources;
	//@Bean
	public MultiResourceItemReader<FlatFileModel> multiResourceItemReader() {
		MultiResourceItemReader<FlatFileModel> resourceItemReader = new MultiResourceItemReader<FlatFileModel>();
		resourceItemReader.setResources(resources);
		resourceItemReader.setDelegate(filereader());
		return resourceItemReader;
	}

}
