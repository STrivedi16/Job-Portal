package com.JobPortal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JobPortal.Dto.JobDto;
import com.JobPortal.Interface.JobInterface;
import com.JobPortal.Responce.ErrorMessage;
import com.JobPortal.Responce.ErrorMessageConstant;
import com.JobPortal.Responce.ErrorMessageKey;
import com.JobPortal.Responce.SuccessMessage;
import com.JobPortal.Responce.SuccessMessageConstant;
import com.JobPortal.Responce.SuccessMessageKey;
import com.JobPortal.Service.JobService;

@RestController
public class JobController {

	@Autowired
	private JobService jobService;
	
	
	@PostMapping("/job")
	@PreAuthorize("hasAuthority	('AddJobs')")
	public ResponseEntity<?> setJobs(@RequestBody JobDto dto)
	{
		try {
			
			JobDto dto2=this.jobService.setJob(dto);
				
			return new ResponseEntity<>(new SuccessMessage(SuccessMessageConstant.JOBS_ADDED, SuccessMessageKey.JOB_M031601, dto2),HttpStatus.OK);
		}
		catch (Exception e) {

			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.JOBS_NOT_STORED, ErrorMessageKey.JOB_E031601),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/showJobs")
	public ResponseEntity<?> showJobs(
			@RequestParam(name = "pagenumber", defaultValue = "0",required = false)Integer pagenumber,
			@RequestParam(name = "pagesize",defaultValue = "5",required = false)Integer pagesize
			)
	{
		
		try {
			
			List<JobInterface> list=this.jobService.searchJob(pagesize, pagenumber);
			
			return new ResponseEntity<>(new SuccessMessage(SuccessMessageConstant.JOB_SHOW, SuccessMessageKey.JOB_M031602, list),HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.JOB_NOT_FOUND, ErrorMessageKey.JOB_E031602),HttpStatus.BAD_REQUEST);
		}
		
	}

}
