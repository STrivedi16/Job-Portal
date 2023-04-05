package com.JobPortal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JobPortal.Dto.RecruterDto;
import com.JobPortal.Responce.ErrorMessage;
import com.JobPortal.Responce.SuccessMessage;
import com.JobPortal.Service.RecrutorService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class RecruterController {

	@Autowired
	private RecrutorService recrutorService;
	
	@PostMapping("/recruter")
	public ResponseEntity<?> setRecruter(@RequestBody RecruterDto dto)
	{
		try {
			RecruterDto dto2=this.recrutorService.setDetails(dto);
			
			return new ResponseEntity<>(new SuccessMessage("Success", "success", dto2),HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage("Recruter  not stored", ""),HttpStatus.BAD_REQUEST);
		}
	}
	
}
