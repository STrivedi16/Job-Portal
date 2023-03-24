package com.JobPortal.OTP;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<OtpEntity, Long> {

	
	OtpEntity findByEmailIgnoreCase(String email);
	
	
}
