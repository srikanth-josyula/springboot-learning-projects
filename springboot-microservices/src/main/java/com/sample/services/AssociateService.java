package com.sample.services;

import java.util.List;
import com.sample.model.Associate;
import com.sample.model.Technology;

public interface AssociateService {
	public List<Associate> retriveAll();
	public Associate retriveUser(int id);
	public Associate createUser(Associate associate);
	public void deleteUser (int id);
	public List<Technology> retrieveAssoTechnologies(int id);
	public Technology createAssocTechnology(int id, Technology technology);
}
