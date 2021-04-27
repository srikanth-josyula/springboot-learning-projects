package com.sample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "technology")
public class Technology {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private int id;


	@NotNull
	@Column(name = "name", nullable = false)
	private String name;

	// Many technologies can be associated with one Associate
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "associateID")
	private Associate associate;

	public Technology() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTechnologyName() {
		return name;
	}

	public void setTechnologyName(String name) {
		this.name = name;
	}

	public Associate getAssociate() {
		return associate;
	}

	public void setAssociate(Associate associate) {
		this.associate = associate;
	}

}
