package com.JobPortal.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JobPortal.FileHandling.FileHelperService;

@RestController
public class FileController {

	@Autowired
	private FileHelperService fileHelperService;

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





}
