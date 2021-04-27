package com.sample.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sample.model.Associate;

@Repository

//CrudRepository or JpaRepository were not designed to work without an <Entity,ID> pair.
public interface NoEntityRepository extends JpaRepository<Associate, String> {

	@Query(value = "SELECT id, name, lastname FROM 'sampleTable'", nativeQuery = true)
	public List<Object[]> getUserDetails();
}
