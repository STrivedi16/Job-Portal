package com.JobPortal.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.JobPortal.Interface.JobInterface;
import com.JobPortal.entity.JobsEntity;

@Repository
public interface JobRepository extends JpaRepository<JobsEntity, Long>{

	
	
	@Query(value = "select j.jobs , j.role,j.discription, j.salary, j.helpline_details, j.company,j.requirement from  jobs_entity j"
			,nativeQuery = true)
	Page<JobInterface> findAll(Pageable pageable, Class<JobInterface> class1);
	
}
