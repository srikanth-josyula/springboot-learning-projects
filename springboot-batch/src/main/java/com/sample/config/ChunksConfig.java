package com.sample.config;

import java.util.HashMap;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;

import com.sample.listeners.ItemCountListener;
import com.sample.model.ItemsProcessorModel;
import com.sample.processor.ValidateItemProcessor;

@Configuration
@EnableBatchProcessing
public class ChunksConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	// Notice they have different ids (you can omit the @Bean annotation and Spring
	// will use the method name) but are using the same @Qualifier value.
	@Bean(name = "chunkJob")
	@Qualifier("job")
	@Order(2)
	public Job chunkJob() {
		return jobBuilderFactory.get("chunkJob").flow(step1()).end().build();
	}

	//SimpleStepBuilder.processor() to set processor instance during setting the tasklets in step.
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<ItemsProcessorModel, ItemsProcessorModel>chunk(10).reader(chuckreader())
				.writer(chuckwriter()).processor(chuckprocessor()).listener(new ItemCountListener()).build();
	}

	@Bean
	public ValidateItemProcessor chuckprocessor() {
		return new ValidateItemProcessor();
	}

	@Bean
	public StaxEventItemReader<ItemsProcessorModel> chuckreader() {
		StaxEventItemReader<ItemsProcessorModel> reader = new StaxEventItemReader<>();
		reader.setResource(new ClassPathResource("ItemsProcessorInput.xml"));
		reader.setFragmentRootElementName("ItemsProcessorData");
		reader.setUnmarshaller(unMarshaller());
		return reader;
	}

	public Unmarshaller unMarshaller() {
		XStreamMarshaller unMarshal = new XStreamMarshaller();
		unMarshal.setAliases(new HashMap<String, Class>() {
			{
				put("ItemsProcessorData", ItemsProcessorModel.class);
			}
		});
		return unMarshal;
	}

	@Bean
	public FlatFileItemWriter<ItemsProcessorModel> chuckwriter() {
		FlatFileItemWriter<ItemsProcessorModel> writer = new FlatFileItemWriter<>();
		writer.setResource(new FileSystemResource("csv/ItemsProcessorOutput.txt"));
		writer.setLineAggregator(new DelimitedLineAggregator<ItemsProcessorModel>() {
			{
				setDelimiter(",");
				setFieldExtractor(new BeanWrapperFieldExtractor<ItemsProcessorModel>() {
					{
						setNames(new String[] { "key", "value", "condition" });
					}
				});
			}
		});
		return writer;
	}
}
