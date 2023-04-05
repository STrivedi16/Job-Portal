package com.JobPortal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JobPortal.entity.Skills;

@Repository
public interface SkillsRepository extends JpaRepository<Skills, Long>{

}
