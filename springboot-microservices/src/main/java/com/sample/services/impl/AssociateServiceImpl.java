package com.sample.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.dao.AssociateRepository;
import com.sample.dao.TechnologyRepository;
import com.sample.exceptions.UserNotFoundException;
import com.sample.model.Associate;
import com.sample.model.Technology;
import com.sample.services.AssociateService;


@Service
public class AssociateServiceImpl implements AssociateService {

	@Autowired
	AssociateRepository associateRepository;
	
	@Autowired
	TechnologyRepository technologyRepository;

	public List<Associate> retriveAll() {
		List<Associate> associateList = associateRepository.findAll();
		return associateList;
	}
	
	public Associate retriveUser(int id) {
		Optional<Associate> associate = associateRepository.findById(id);
		if (!associate.isPresent())
			throw new UserNotFoundException("id-" + id);
		return associate.get();
	}
	
	public Associate createUser(Associate associate) {
		Associate savedassociate = associateRepository.save(associate);
		return savedassociate;
	}
	
	public void deleteUser (int id) {
		associateRepository.deleteById(id);
	}
	
	// get associate technologies
	public List<Technology> retrieveAssoTechnologies(int id) {
		Optional<Associate> associate = associateRepository.findById(id);
		if (!associate.isPresent())
			throw new UserNotFoundException("id-" + id);
		return associate.get().getTechnologies();
	}

	// post associate technologies
	public Technology createAssocTechnology(int id, Technology technology) {
		Optional<Associate> assocOptional = associateRepository.findById(id);
		if (!assocOptional.isPresent())
			throw new UserNotFoundException("id-" + id);
		Associate  associate = assocOptional.get();
		technology.setAssociate(associate);
		return technologyRepository.save(technology);
	}
	
}
