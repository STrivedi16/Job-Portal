package com.JobPortal.FileHandling;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JobPortal.Interface.UsersJobsInterface;
import com.JobPortal.Repository.UserRepository;
import com.JobPortal.entity.UserEntity;

@Service
public class FileHelperService {

	@Autowired
	
	private UserRepository repository;
	
	public void getAllUser(HttpServletResponse response) throws IOException
	{
		List<UserEntity> list=this.repository.findAll();
		
		
		
		XSSFWorkbook  workbook=new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet();
		
		
		XSSFRow row=sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("Name");
		row.createCell(2).setCellValue("Email");
		row.createCell(3).setCellValue("City");
		row.createCell(4).setCellValue("State");
		
		int dataRow=1;
		for(UserEntity entity:list)
		{
			XSSFRow data=sheet.createRow(dataRow);
			data.createCell(0).setCellValue(entity.getId());
			data.createCell(1).setCellValue(entity.getName());
			data.createCell(2).setCellValue(entity.getEmail());
			data.createCell(3).setCellValue(entity.getCity());
			data.createCell(4).setCellValue(entity.getState());
			dataRow++;
		}
		
		ServletOutputStream outputStream=response.getOutputStream();
		
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
		
		
	}
	
	public void getUserJob(HttpServletResponse response) throws IOException
	{
		List<UsersJobsInterface>list=this.repository.findAll(UsersJobsInterface.class);
		
		
		
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet();
		
		XSSFRow row=sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("Name");
		row.createCell(2).setCellValue("Email");
		row.createCell(3).setCellValue("Jobs");
		row.createCell(4).setCellValue("Role");
		row.createCell(5).setCellValue("Company");
		
		int dataRow=0;
		int records=1;
		for(UsersJobsInterface user: list)
		{
			XSSFRow data=sheet.createRow(dataRow);
			data.createCell(0).setCellValue(records);
			data.createCell(1).setCellValue(user.getName());
			data.createCell(2).setCellValue(user.getEmail());
			data.createCell(3).setCellValue(user.getJobs());
			data.createCell(4).setCellValue(user.getRole());
			data.createCell(5).setCellValue(user.getCompany());
			dataRow++;
			records++;
		}
		
		ServletOutputStream outputStream=response.getOutputStream();
		
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
}
