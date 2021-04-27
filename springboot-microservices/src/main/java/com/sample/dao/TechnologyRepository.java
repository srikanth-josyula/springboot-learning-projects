package com.sample.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.model.Technology;

@Repository
public interface  TechnologyRepository extends JpaRepository<Technology, Integer>{

}
