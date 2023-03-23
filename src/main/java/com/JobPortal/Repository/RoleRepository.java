package com.JobPortal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JobPortal.entity.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long>{

}
