package com.sample.utils;
/**
 * This is One way of using the HATEOAS at the class Level
 * But for this the User ID should not be same as the table name 
 * this adds more links too in the response
 **/

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import com.sample.controllers.UserController;
import com.sample.model.User;

public class UserUtils {

	public List<Resource<User>> userResponse(List<User> userList) {
		List<Resource<User>> titleArray = new ArrayList<Resource<User>>();
		for (User user : userList) {
			
			Resource<User> resource = new Resource<User>(user);
			 //Adding method link user 'singular' resource
	        Link userLink = ControllerLinkBuilder
	                .linkTo(ControllerLinkBuilder
	                .methodOn(UserController.class).retrieveUser(user.getUserid()))
	                .withRel("user-details");
	        //Add link to singular resource
	        resource.add(userLink);
			titleArray.add(resource);
		}
		return titleArray;
	}

}
