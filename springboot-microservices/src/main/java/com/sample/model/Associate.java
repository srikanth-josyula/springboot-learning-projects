package com.sample.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "associate")
public class Associate {

	private int sono;
	private int associateID;
	private String associateName;
	private String associateEmailId;
	private String gender;
	private int experience;
	private List<Technology> technology;

	public Associate() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sono", nullable = false)
	public int getSono() {
		return sono;
	}

	public void setSono(int sono) {
		this.sono = sono;
	}

	@NotNull
	@Column(name = "associate_name", nullable = false)
	@Size(min = 2, message = "Name should have atleast 2 characters")
	@ApiModelProperty(notes = "Name should have atleast 2 characters")
	public String getAssociateName() {
		return associateName;
	}

	public void setAssociateName(String associateName) {
		this.associateName = associateName;
	}

	@NotNull
	@Column(name = "associateid", nullable = false)
	public int getAssociateID() {
		return associateID;
	}

	public void setAssociateID(int associateID) {
		this.associateID = associateID;
	}

	@NotNull
	@Column(name = "associate_email_id", nullable = false)
	public String getAssociateEmailId() {
		return associateEmailId;
	}

	public void setAssociateEmailId(String associateEmailId) {
		this.associateEmailId = associateEmailId;
	}

	@Column(name = "gender")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@NotNull
	@Column(name = "experience", nullable = false)
	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	// One Associate can have many technology skills
	@OneToMany(mappedBy = "associate", fetch = FetchType.EAGER, targetEntity = Technology.class)
	public List<Technology> getTechnologies() {
		return technology;
	}

	public void setTechnologies(List<Technology> technology) {
		this.technology = technology;
	}

	@Override
	public String toString() {
		return "Employee [Sono=" + sono + ", associateName=" + associateName + ", associateID=" + associateID
				+ ", associateEmailId=" + associateEmailId + ", gender=" + gender + "]";
	}

}
