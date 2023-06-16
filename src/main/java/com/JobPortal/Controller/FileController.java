package com.JobPortal.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.type.descriptor.java.MutabilityPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.JobPortal.FileHandling.FileHelperService;
import com.JobPortal.FileHandling.FileService;
import com.JobPortal.Responce.ErrorMessage;
import com.JobPortal.Responce.ErrorMessageConstant;
import com.JobPortal.Responce.ErrorMessageKey;
import com.JobPortal.Responce.SuccessMessageConstant;
import com.JobPortal.Responce.SuccessMessageKey;
import com.JobPortal.Responce.SuccessMsg;
import com.netflix.discovery.converters.Auto;

@RestController
public class FileController {

	@Autowired
	private FileHelperService fileHelperService;

	@Autowired
	private FileHelperService service;
	
	
	
	
	@PostMapping("/skillsFile")
	@PreAuthorize("hasAuthority	('uploadFile')")
	public ResponseEntity<?> setSkills(@RequestParam("file") MultipartFile file)
	{
		try {
			
				if(file.isEmpty())
				{
					return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.FILE_NOT_PROPER, ErrorMessageKey.FILE_E031802),HttpStatus.NOT_ACCEPTABLE);

				}
				
				this.service.save(file);
				
				return new ResponseEntity<>(new SuccessMsg(SuccessMessageConstant.FILE_UPLOAD, SuccessMessageKey.FILE_M031901),HttpStatus.OK);
			
			
				}
		catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.FILE_NOT_STORED, ErrorMessageKey.FILE_E031801),HttpStatus.BAD_REQUEST);

		}
	}
	
	@PostMapping("/userfile")
	@PreAuthorize("hasAuthority	('uploadFile')")
	public ResponseEntity<?> setUserdata(@RequestParam("file") MultipartFile file)
	{
		

		
		try {
			if(file.isEmpty())
			{
				
				return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.FILE_NOT_PROPER, ErrorMessageKey.FILE_E031802),HttpStatus.NOT_ACCEPTABLE);

			}
			
			this.service.saveUser(file);
			
			return new ResponseEntity<>(new SuccessMsg(SuccessMessageConstant.FILE_UPLOAD, SuccessMessageKey.FILE_M031901),HttpStatus.OK);
			

		}
		catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.FILE_NOT_STORED, ErrorMessageKey.FILE_E031801),HttpStatus.BAD_REQUEST);

		}
	}
	
	
	@GetMapping("/userFile")
	public void generateExcel(HttpServletResponse response) throws IOException
	{
		response.setContentType("appication/octet-stream");
		
		String headerKey="Content-Disposition";
		String headerValue="attachment;filename=userdata.xls";
		
		response.setHeader(headerKey, headerValue);
		this.fileHelperService.getAllUser(response);
	}
	
	
	@GetMapping("/users/jobs")
	
	public void excelUserJobs(HttpServletResponse response) throws IOException
	{
		response.setContentType("appication/octet-stream");
		
		String headerKey="Content-Disposition";
		String headerValue="attachment;filename=userJobdata.xls";
		
		response.setHeader(headerKey, headerValue);
		this.fileHelperService.getUserJob(response);
		
	}

	@Autowired
	private FileService fileService;
	
	@PostMapping("/uploadCsv")
	public String uploadCsv(@RequestParam("file") MultipartFile file)
	{
		
		return  this.fileService.uploadCsv(file);
	}

	@PostMapping("/multitableFile")
	@PreAuthorize("hasAuthority	('uploadFile')")
	public ResponseEntity<?> addDataToMultiTable(@RequestParam("file") MultipartFile file)
	{
		

		
		try {
			if(file.isEmpty())
			{
				
				return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.FILE_NOT_PROPER, ErrorMessageKey.FILE_E031802),HttpStatus.NOT_ACCEPTABLE);

			}
			
			else if(fileService.checkFileFormat(file))
			{
				this.fileService.setDataInMultitable(file);
				return new ResponseEntity<>(new SuccessMsg(SuccessMessageConstant.FILE_UPLOAD, SuccessMessageKey.FILE_M031901),HttpStatus.OK);
				
			}
			
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.FILE_NOT_PROPER, ErrorMessageKey.FILE_E031802),HttpStatus.NOT_ACCEPTABLE);

		}
		catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.FILE_NOT_STORED, ErrorMessageKey.FILE_E031801),HttpStatus.BAD_REQUEST);

		}
	}


}
