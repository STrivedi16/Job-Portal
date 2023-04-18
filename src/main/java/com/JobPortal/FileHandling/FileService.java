package com.JobPortal.FileHandling;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.JobPortal.Repository.UserCsvRepo;
import com.JobPortal.entity.Skills;
import com.JobPortal.entity.UserCsv;
import com.JobPortal.entity.UserEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Controller;

import java.io.InputStreamReader;
import java.io.Reader;

@Component
public class FileService {


		public  boolean checkFileFormat(MultipartFile file) {
			if (file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
				return true;
			} else {
				return false;
			}
		}

		public  List<Skills> storeToDb(MultipartFile file) throws Exception {
			List<Skills> coments = new ArrayList<>();

			try {
				XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());

				XSSFSheet sheet = workbook.getSheetAt(0);

				Iterator<Row> iterator = sheet.iterator();

				int rowNumber = 0;
				while (iterator.hasNext()) {
					Row row = iterator.next();
					if (rowNumber == 0) {
						rowNumber++;
						continue;
					}

					Iterator<Cell> cells = row.iterator();

					int cid = 0;
					Skills coment = new Skills();
					while (cells.hasNext()) {
						Cell cell = cells.next();

						switch (cid) {
						case 0:

							coment.setId((long) cell.getNumericCellValue());
							break;
						case 1:
							coment.setSkills(cell.getStringCellValue());
							break;
						case 2:
							coment.setActive(cell.getBooleanCellValue());
							break;
						default:
							break;
						}
						cid++;
					}

					coments.add(coment);
				}

			} catch (Exception e) {
				throw new Exception("not stored");
			}
			return coments;
		}
		
		
		public List<UserEntity> setData(MultipartFile file) throws Exception
		{
			
			List<UserEntity> list=new ArrayList<>();
	

			try {
				XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());

				XSSFSheet sheet = workbook.getSheetAt(0);

				Iterator<Row> iterator = sheet.iterator();

				int rowNumber = 0;
				while (iterator.hasNext()) {
					Row row = iterator.next();
					if (rowNumber == 0) {
						rowNumber++;
						continue;
					}

					Iterator<Cell> cells = row.iterator();

					int cid = 0;
				UserEntity entity=new UserEntity();
					while (cells.hasNext()) {
						Cell cell = cells.next();

						switch (cid) {
//						case 0:
//
//							coment.setId((long) cell.getNumericCellValue());
//							break;
						case 0:
							entity.setName(cell.getStringCellValue());
							break;
						case 1:
							entity.setCity(cell.getStringCellValue());
							break;
						case 2:
							entity.setEmail(cell.getStringCellValue());
							break;
						case 3:
							entity.setPassword(cell.getStringCellValue());
							break;
						case 4:
							entity.setMobileNo(cell.getStringCellValue());
							break;
						case 5:
							entity.setState(cell.getStringCellValue());
							break;
							
							
						default:
							break;
						}
						cid++;
					}

					list.add(entity);
				}

			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("not stored");
			}
			return list;
		
	}

		
		@Autowired
		private UserCsvRepo repo;
		
		public String uploadCsv(MultipartFile  file)
		{
			try {
				Reader reader=new InputStreamReader(file.getInputStream());
				
				Iterable<CSVRecord> records=CSVFormat.DEFAULT.withHeader().parse(reader);
				
				for(CSVRecord record : records)
				{
					int  id= Integer.parseInt(record.get("id"));
					String name=record.get("first_name");
					String email=record.get("email");
					String gender=record.get("gender");
					
					UserCsv  csv=new UserCsv();
					csv.setId(id);
					csv.setName(name);
					csv.setEmail(email);
					csv.setGender(gender);
					
					this.repo.save(csv);
					
				}
				
				
				return "data Stored";
					
						
			}
			catch (Exception e) {
				
				return e.getMessage();
			}
		}
	
}
