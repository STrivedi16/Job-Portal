package com.JobPortal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import com.JobPortal.entity.UserTest;

@Repository
public interface UserTestRepo extends JpaRepository<UserTest, Integer> {

	@Query(name = "select * from  testing.user_test where id=: id", nativeQuery = true)
	UserTest findByName(@Param("id") int id);
	
}

