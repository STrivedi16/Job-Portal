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

import com.JobPortal.Repository.UserRepository;
import com.JobPortal.entity.UserEntity;

@Service
public class FileHelperService {

	@Autowired
	
	private UserRepository repository;
	
	public void getToExcel(HttpServletResponse response) throws IOException
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
			data.createCell(dataRow).setCellValue(entity.getId());
			data.createCell(dataRow).setCellValue(entity.getName());
			data.createCell(dataRow).setCellValue(entity.getEmail());
			data.createCell(dataRow).setCellValue(entity.getCity());
			data.createCell(dataRow).setCellValue(entity.getState());
			dataRow++;
		}
		
		ServletOutputStream outputStream=response.getOutputStream();
		
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
		
		
	}
}
