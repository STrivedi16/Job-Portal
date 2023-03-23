package com.JobPortal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JobPortal.entity.UserRolesEntity;
@Repository
public interface UserRoleRepository extends JpaRepository<UserRolesEntity, Long>{

}
