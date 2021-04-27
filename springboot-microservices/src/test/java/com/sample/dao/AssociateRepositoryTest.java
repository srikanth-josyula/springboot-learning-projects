package com.sample.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sample.model.Associate;


@RunWith(SpringRunner.class)
@DataJpaTest
public class  AssociateRepositoryTest {
	@Autowired 
	AssociateRepository repository;
	
	@Test
	public void testFindAll() {
		List<Associate> associate = repository.findAll();
		assertEquals(0,associate.size());
	}

}
