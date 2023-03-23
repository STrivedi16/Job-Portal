package com.JobPortal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JobPortal.entity.RolesPermissionEntity;

@Repository
public interface RolePermissionsRepository extends JpaRepository<RolesPermissionEntity, Long>{

}
