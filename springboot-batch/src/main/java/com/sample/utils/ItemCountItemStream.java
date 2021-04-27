package com.sample.utils;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;

public class ItemCountItemStream implements ItemStream {

	public void open(ExecutionContext executionContext) throws ItemStreamException {
	}

	public void update(ExecutionContext executionContext) throws ItemStreamException {
		System.out.println("ItemCount: " + executionContext.get("FlatFileItemReader.read.count"));
	}

	public void close() throws ItemStreamException {
	}
}