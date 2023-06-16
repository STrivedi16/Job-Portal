package com.JobPortal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.JobPortal.Dto.RecruterDto;
import com.JobPortal.entity.RecruterEntity;

@Repository
public interface RecruterRepo  extends JpaRepository<RecruterEntity, Long>{

	@Query(value = "select r.name , r.company_discription , r.company_email, r.registration_number, r.status from recruter_entity r",nativeQuery = true)
	List<RecruterDto> findAll(Class<RecruterDto> class1);
}
