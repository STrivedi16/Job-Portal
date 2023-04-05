package com.JobPortal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JobPortal.entity.RecruterEntity;

@Repository
public interface RecruterRepo  extends JpaRepository<RecruterEntity, Long>{

}
