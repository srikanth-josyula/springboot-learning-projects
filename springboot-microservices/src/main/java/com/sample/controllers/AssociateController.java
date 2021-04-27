package com.sample.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sample.model.Associate;
import com.sample.model.Technology;
import com.sample.services.AssociateService;

@RestController
@RequestMapping("/associate")
public class AssociateController {

	@Autowired
	AssociateService associateService;

	/**
	 * GET All associate details 
	 **/
	
	// by Default it takes get so we need not
	@RequestMapping("/getassociates")
	public List<Associate> fetchAssociateDetails() {
		return associateService.retriveAll();
	}

	/**
	 * GET specific associate details with HATEOAS Implementation
	 **/
	@RequestMapping(
			value = "/getassociate/{id}",
			method = RequestMethod.GET,
			produces = "application/json"
			)
	public Resource<Associate> fetchAssociatebyID(@PathVariable("id") String id) {
		Associate associate = associateService.retriveUser(Integer.valueOf(id));
		
		// Method level HATEOAS implementation
		Resource<Associate> resource = new Resource<Associate>(associate);
		Link associatesLink = ControllerLinkBuilder
        .linkTo(ControllerLinkBuilder
                .methodOn(this.getClass()).fetchAssociateDetails())
                .withRel("all-associate-details");
        //Add link to singular resource
        resource.add(associatesLink);

		return resource;
		//return associateService.retriveUser(Integer.valueOf(id));
	}
	
	/**
	 * GET specific associate Technologies
	 **/
	@GetMapping("/associate/{id}/technologies")
	public List<Technology> fetchAssoSkills(@PathVariable int id) {
		return associateService.retrieveAssoTechnologies(id);
	}

	
	/**
	 * POST create associate
	 **/
	@RequestMapping(
			value = "/associate",
			method = RequestMethod.POST,
			produces = "application/json"
			)
	public ResponseEntity<Object> createAssociate(@Valid @RequestBody Associate associate) {
		Associate  newAssociate =  associateService.createUser(associate);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newAssociate.getSono())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PostMapping("/associate/{id}/technologies")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Technology technology) {
		Technology  savedtechnology =  associateService.createAssocTechnology(Integer.valueOf(id),technology);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedtechnology.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	/**
	 * DELETE Associate
	 **/

	@RequestMapping(
			value = "/delete/{id}", 
			method = RequestMethod.DELETE)
	public void deleteAssociate(@PathVariable("id") String id) {
		 associateService.deleteUser(Integer.valueOf(id));
	}

}
