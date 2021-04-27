package com.sample.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ItemsProcessorModel")
public class ItemsProcessorModel {

	private String key;
	private String value;
	private boolean condition;

	@XmlElement(name = "key")
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@XmlElement(name = "value")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@XmlElement(name = "condition")
	public boolean getCondition() {
		return condition;
	}

	public void setCondition(boolean condition) {
		this.condition = condition;
	}

	@Override
	public String toString() {
		return "ItemsProcessorModel [key=" + key + ", value=" + value + ", condition=" + condition + "]";
	}

}