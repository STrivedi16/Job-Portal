package com.JobPortal.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.JobPortal.Interface.JobInterface;
import com.JobPortal.entity.JobsEntity;

@Repository
public interface JobRepository extends JpaRepository<JobsEntity, Long>{

	
	
	@Query(value = "select j.jobs , j.role,j.discription, j.salary, j.helpline_details, j.company,j.requirement from  jobs_entity j where j.opening > 0"
			,nativeQuery = true)
	Page<JobInterface> findAll(Pageable pageable, Class<JobInterface> class1);
	
	@Query(value = "select  j.jobs , j.role,j.discription, j.salary, j.helpline_details, j.company,j.requirement from  jobs_entity j where j.opening > 0 and  ORDER by j.id DESC"	,nativeQuery = true)
	Page<JobInterface> findAllLettest(Pageable pageable, Class<JobInterface> class1);
	
	 @Query(value = "select  j.jobs , j.role,j.discription, j.salary, j.helpline_details, j.company, j.requirement "
	            + "from jobs_entity j "
	            + "where lower(j.role) = lower(:searchTerm) and j.opening > 0", nativeQuery = true)
		Page<JobInterface> findByRoleIgnoreCase(@Param("searchTerm") String roles, Pageable pageable, Class<JobInterface> class1);
	
	 @Query(value = "select  j.jobs ,j.role,j.discription, j.salary, j.helpline_details, j.company, j.requirement , j.city, j.state\r\n"
	 		+ "	            from jobs_entity j \r\n"
	 		+ "	             where lower(j.state) = lower(:searchState) and j.opening > 0" , nativeQuery = true)
	 Page<JobInterface> findByStatesIgnoreCase(@Param("searchState") String state, Pageable pageable, Class<JobInterface> class1);
	
	 @Query(value = "select  j.company , j.discription , j.jobs , j.requirement , j.salary, j.helpline_details , j.opening , j.state , j.city, j.role from jobs_entity j where lower(j.state) = lower(:searchState) and lower(j.role) = lower(:role) and j.opening > 0\r\n"
	 		+ "",nativeQuery = true)
	 Page<JobInterface> findByStatesIgnoreCaseAndRoleIgnoreCase(@Param("searchState")String state, @Param("role") String role,Pageable pageable, Class<JobInterface> class1 );
}
