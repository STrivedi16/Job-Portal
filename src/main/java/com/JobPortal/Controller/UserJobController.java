package com.JobPortal.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JobPortal.Config.JwtFilter;
import com.JobPortal.Dto.UserJobDto;
import com.JobPortal.Interface.UserInterface;
import com.JobPortal.Interface.UserJobInterface;
import com.JobPortal.Interface.UsersJobsInterface;
import com.JobPortal.OTP.EmailService;
import com.JobPortal.Responce.ErrorMessage;
import com.JobPortal.Responce.ErrorMessageConstant;
import com.JobPortal.Responce.ErrorMessageKey;
import com.JobPortal.Responce.ResourcesNotFoundException;
import com.JobPortal.Responce.SuccessMessage;
import com.JobPortal.Responce.SuccessMessageConstant;
import com.JobPortal.Responce.SuccessMessageKey;
import com.JobPortal.Service.UserJobService;
import com.JobPortal.entity.UserJobsEntity;
import com.JobPortal.entity.UserProfileJobsResponse;

@RestController
public class UserJobController {

	@Autowired
	private UserJobService jobService;
	
	@Autowired
	private JwtFilter filter;
	
	@Autowired
	private EmailService emailService;
	
	
	
	@PostMapping("/applyforjob")
	@PreAuthorize("hasAuthority	('applyforJobs')")
	public ResponseEntity<?> userApplyJob(@RequestBody UserJobDto dto)
	{
		
		
		
		try {
			UserJobDto dto2=this.jobService.setUserJob(dto);
			
			return new ResponseEntity<>(new SuccessMessage(SuccessMessageConstant.USER_JOB_APPLY, SuccessMessageKey.USER_JOB_M031701, dto2),HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.USER_NOT_APPLY, ErrorMessageKey.USER_JOB_E031701),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/user/jobs")
	//@PreAuthorize("hasAuthority	('applyforJobs')")
	public ResponseEntity<?> showUserJob(HttpServletRequest request , 
			@RequestParam(name = "pagenumber", defaultValue = "0",required = false)Integer pagenumber,
			@RequestParam(name="pagesize",defaultValue = "5",required = false)Integer pagesize
			)
	{
		try {
			
			long id= (long) request.getAttribute("userId");
			
			List<UserJobInterface> list=this.jobService.getUserJob(id, pagenumber, pagesize);
	
			return new ResponseEntity<>(new SuccessMessage(SuccessMessageConstant.USER_JOBS, SuccessMessageKey.USER_JOB_M031702, list),HttpStatus.BAD_REQUEST);
			}
			
			
		catch (ResourcesNotFoundException e) {
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.JOB_NOT_FOUND, ErrorMessageKey.JOB_E031602),HttpStatus.BAD_REQUEST);
		}
		
		}
		
			
	@GetMapping("/users/jobs/{id}")
	@PreAuthorize("hasAuthority	('showUserJobs')")
	public ResponseEntity<?> showUsersJobs(@PathVariable("id") long id , 
			@RequestParam(name = "pagenumber", defaultValue = "0",required = false)Integer pagenumber,
			@RequestParam(name="pagesize",defaultValue = "5",required = false)Integer pagesize
			)
	{
		try {
			
			
			
			List<UserJobInterface> list=this.jobService.getUserJob(id, pagenumber, pagesize);
	
			return new ResponseEntity<>(new SuccessMessage(SuccessMessageConstant.USER_JOBS, SuccessMessageKey.USER_JOB_M031702, list),HttpStatus.BAD_REQUEST);
			
		}
		catch (ResourcesNotFoundException e) {
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.JOB_NOT_FOUND, ErrorMessageKey.JOB_E031602),HttpStatus.BAD_REQUEST);
		}
		
		}
		
		
	@GetMapping("/showCandidate")
	public ResponseEntity<?> showCandidate(
			@RequestParam(name = "pagenumber", defaultValue = "0",required = false)Integer pagenumber,
			@RequestParam(name="pagesize",defaultValue = "5",required = false)Integer pagesize,
			@RequestParam(name = "Company",required = false)String company
			
			){
		
		try {
			
			List<UserInterface> list=this.jobService.getAllCandidate(company, pagenumber, pagesize);
			
			return new ResponseEntity<>(new SuccessMessage(SuccessMessageConstant.USER_DETAILS, SuccessMessageKey.USER_M031102, list),HttpStatus.OK);
			
		}
		catch (ResourcesNotFoundException e) {
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.USER_NOT_FOUND, ErrorMessageKey.USER_E031102),HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PatchMapping("/userjobStatus/{id}")
	@PreAuthorize("hasAuthority	('CandidateReview')")
	public ResponseEntity<?> updateStatus(@PathVariable("id") long id , @RequestBody UserJobDto  dto)
	{
		try {
			
			
			
			UserJobsEntity entity=this.jobService.updateStatus(id, dto);
			
			String message="<h3> Hello "+" "+entity.getUserEntity().getName()+"</h3><br> "+
			"<h4><p>"+"Your application status "+" of "+entity.getJobEntity().getCompany()+ " as "+entity.getJobEntity().getJobs() + "  has been Updated to "+"</h4></p>"+
			"<h2><b> "+ entity.getStatus()+"</h2></b>"
			;
			
			
			String subject="Application Status Change";
			
			
			String to=entity.getUserEntity().getEmail();
			
			this.emailService.sendOtp(subject, message, to);
			
			
			return new ResponseEntity<>(new SuccessMessage(SuccessMessageConstant.USER_JOB_STATUS, SuccessMessageKey.USER_JOB_M031704, entity.getStatus()),HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.USER_JOB_STATUS, ErrorMessageKey.USER_JOB_E031704),HttpStatus.BAD_REQUEST);
		}
	}
	
		
	
	@GetMapping("/applyCandidate")
	@PreAuthorize("hasAuthority	('showUserJobs')")
	public ResponseEntity<?> getApplyCandidates(HttpServletRequest request)
	{
		
		try {
			
			long id= (long) request.getAttribute("userId");
			
			ArrayList<UserProfileJobsResponse>  list=this.jobService.getCandidates(id);
			
			return new ResponseEntity<>(new SuccessMessage(SuccessMessageConstant.USER_JOBS, SuccessMessageKey.USER_JOB_M031702, list),HttpStatus.OK);
			}
			
		
		catch (ResourcesNotFoundException e) {
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.JOB_NOT_FOUND, ErrorMessageKey.JOB_E031602),HttpStatus.BAD_REQUEST);

		}
	}
	
	
	}

	
	



