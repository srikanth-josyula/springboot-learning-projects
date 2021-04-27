package com.sample.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sample.model.Associate;

@Repository
public interface AssociateRepository extends JpaRepository<Associate, Integer> {

	// Custom Queries model using TypedQuery <-- NEED To TEST -->
	public static Associate findByName(EntityManager em, String name) {
		TypedQuery<Associate> query = em.createQuery("SELECT * FROM Associate WHERE ASSOCIATENAME :name",
				Associate.class);
		return query.setParameter("name", name).getSingleResult();
	}

	// Using Native Query for Joining of tables .. This object returns a new table as seen in SQL Developer with new rows 
	// so we need to iterate over the object to get the values
	@Query(value = "select t.associateid, t.associate_name , t.technology, t.schedule_interview , p.panel , p.Interviewer , "
			+ "t.interview_status  from Associate t inner join panelassociation p on t.associateid = p.associateid  where t.schedule_interview >"
			+ " ?1 and t.schedule_interview < ?2", nativeQuery = true)
	public List<Object[]> findByCriteria(String startDate, String endDate, String panel, String opportunity);
}
