package com.JobPortal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JobPortal.entity.Permissions;

@Repository
public interface PermissionsRepository extends JpaRepository<Permissions, Long> {

	
}
