package com.JobPortal.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.JobPortal.Interface.UserInterface;
import com.JobPortal.Interface.UserJobInterface;
import com.JobPortal.Interface.UsersJobsInterface;
import com.JobPortal.entity.UserJobsEntity;

@Repository
public interface UserJobRepository extends JpaRepository<UserJobsEntity, Long>{


	
	//List<UserJobInterface> findById(@Param("id") long id, Class<UserJobInterface> class1);
	
	
	
	@Query(value = "select j.jobs, j.company, j.discription, j.requirement, j.role, j.salary, j.helpline_details from user_entity u \r\n"
			+ "join user_jobs_entity uje on u.id = uje.user_entity_id\r\n"
			+ "join jobs_entity  j on j.id=uje.job_entity_id where u.id= :id",nativeQuery = true)
	Page<UserJobInterface> findById(@Param("id")   long id, Pageable pageable, Class<UserJobInterface> class1);

	
	
	
	@Query(value = "select u.name, u.email, u.state, u.mobile_no , u.city from user_entity u \r\n"
			+ "join user_jobs_entity uje on u.id= uje.user_entity_id\r\n"
			+ "join jobs_entity j on j.id = uje.job_entity_id where j.company= :name", nativeQuery = true)
	Page<UserInterface> getAllCandidats(String name, Class<UserInterface> class1, Pageable pageable);
	
	@Query(value = "select u.name , u.email,u.city,u.state, j.role , j.jobs from user_entity u\r\n"
			+ "join user_jobs_entity uje on u.id= uje.user_entity_id\r\n"
			+ "join jobs_entity j on j.id= uje.job_entity_id where j.created_by= :username ",nativeQuery = true)
	List<UsersJobsInterface> getApplyCandidates(@Param("username") long username, Class<UsersJobsInterface>Class);
}
