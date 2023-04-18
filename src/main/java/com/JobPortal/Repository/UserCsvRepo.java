package com.JobPortal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JobPortal.entity.UserCsv;

@Repository
public interface UserCsvRepo extends JpaRepository<UserCsv, Integer>{

}
