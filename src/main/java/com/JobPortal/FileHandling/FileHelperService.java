package com.JobPortal.FileHandling;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.multi.MultiButtonUI;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.xml.DomUtils;
import org.springframework.web.multipart.MultipartFile;

import com.JobPortal.Interface.JobsUsersInterface;
import com.JobPortal.Interface.UsersJobsInterface;
import com.JobPortal.Repository.SkillsRepository;
import com.JobPortal.Repository.UserRepository;
import com.JobPortal.entity.Skills;
import com.JobPortal.entity.UserEntity;


@Service
public class FileHelperService {

	@Autowired
	
	private UserRepository repository;
	
	@Autowired
	private SkillsRepository skillsRepository;
	
	@Autowired
	private FileService fileService;
	
	public void save(MultipartFile  file)
	{
		try {
			
			if(fileService.checkFileFormat(file))
			{
				List<Skills> list=fileService.storeToDb(file);
				
				this.skillsRepository.saveAll(list);
			}
			else {
				throw new Exception("file not matched");
			}
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveUser(MultipartFile file) throws Exception
	{
		try {
			if(fileService.checkFileFormat(file))
			{
				List<UserEntity> list=fileService.setData(file);
				this.repository.saveAll(list);
			}
			else {
				throw new Exception("file not matched");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception("file data  not stored");
		}
	}
	
	
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
		List<JobsUsersInterface>list=this.repository.findall(JobsUsersInterface.class);
		
		
		
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
		for(JobsUsersInterface user: list)
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
