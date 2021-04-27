package com.sample.processor;

import org.springframework.batch.item.ItemProcessor;
import com.sample.model.ItemsProcessorModel;

//ItemProcessor to add business logic after reading the input and before passing it to writer for writing to the file/database.
public class ValidateItemProcessor implements ItemProcessor<ItemsProcessorModel, ItemsProcessorModel> {

	@Override
	public ItemsProcessorModel process(ItemsProcessorModel item) throws Exception {
		System.out.println("Processing result :" + item);
		if (item.getKey() == null || "".equalsIgnoreCase(item.getKey())) {
			System.out.println("Invalid Key "+item.getKey());
			return null;
		}
		if (!item.getCondition()) {
			System.out.println("Invalid Condition "+item.getKey());
			return null;
		}
		return item;
	}

}