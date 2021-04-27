package com.sample.utils;

import org.springframework.batch.item.ItemProcessor;
import com.sample.model.FlatFileModel;
 
public class DBLogProcessor implements ItemProcessor<FlatFileModel, FlatFileModel>
{
    public FlatFileModel process(FlatFileModel flatfileModel) throws Exception
    {
        System.out.println("Inserting model : " + flatfileModel);
        return flatfileModel;
    }
}
