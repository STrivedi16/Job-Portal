package com.JobPortal.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.JobPortal.Interface.UserInterface;
import com.JobPortal.Interface.UserJobInterface;
import com.JobPortal.Interface.UserPermission;
import com.JobPortal.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

	UserEntity findByEmailIgnoreCase(String email);
	
	List<UserInterface> findById(long id , Class<UserInterface>class1);
	
	@Query(value = "select u.name, r.roles,p.permissions  from user_entity u\r\n"
			+ "join user_roles_entity ure on u.id=ure.users_id\r\n"
			+ "join roles r on ure.roles_id=r.id\r\n"
			+ "join roles_permission_entity rpe on r.id=rpe.role_entity_id\r\n"
			+ "join permissions p on p.id=rpe.permissions_id where u.id= :idnumber",nativeQuery = true)
	List<UserPermission> getPermission(@Param("idnumber")  long id , Class<UserPermission> class1);

	
//	@Query(value = "select u.name, u.email, u.city, u.state , u.mobile_no from user_entity u",nativeQuery = true)
//	List<UserInterface> findAll(Class<UserInterface> class1);

	
	@Query(value = "select u.name, u.email, u.city, u.state , u.mobile_no from user_entity u",nativeQuery = true)
	Page<UserInterface> findAll(Pageable pageable, Class<UserInterface> class1);
	
	
	
}
