package com.rapidpro.messaging.model;

import java.io.Serializable;
import java.util.Date;

public class StagingMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date timeStamp;
	private Long id;
	private String processtype;
	private String csvfile;
	private String fileslocation;

	public StagingMessage() {
	}

	public StagingMessage(Date timeStamp, Long id, String processtype, String csvfile, String fileslocation) {
		super();
		this.timeStamp = timeStamp;
		this.id = id;
		this.processtype = processtype;
		this.csvfile = csvfile;
		this.fileslocation = fileslocation;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProcesstype() {
		return processtype;
	}

	public void setProcesstype(String processtype) {
		this.processtype = processtype;
	}

	public String getCsvfile() {
		return csvfile;
	}

	public void setCsvfile(String csvfile) {
		this.csvfile = csvfile;
	}

	public String getFileslocation() {
		return fileslocation;
	}

	public void setFileslocation(String fileslocation) {
		this.fileslocation = fileslocation;
	}

	@Override
	public String toString() {
		return "StagingMessage [timeStamp=" + timeStamp + ", id=" + id + ", processtype=" + processtype + ", csvfile="
				+ csvfile + ", fileslocation=" + fileslocation + "]";
	}

	
}