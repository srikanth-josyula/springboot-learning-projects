package com.sample.model;

public class FlatFileModel {
	
	private String key;
	private String value;
	private boolean condition;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean getCondition() {
		return condition;
	}

	public void setCondition(boolean condition) {
		this.condition = condition;
	}

	@Override
	public String toString() {
		return "FlatFileModel [key=" + key + ", value=" + value + ", condition=" + condition + "]";
	}
}
