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

import com.JobPortal.entity.Skills;
import com.JobPortal.entity.UserEntity;

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
				
				
				XSSFWorkbook workbook=new XSSFWorkbook();
				XSSFSheet sheet=workbook.getSheetAt(0);
					Iterator<Row> iterator=sheet.iterator();
					
					int rowNumber=0;
					
					while(iterator.hasNext())
					{
						Row row=iterator.next();
						if(rowNumber==0)
						{
							rowNumber++;
							continue;
						}
						UserEntity entity=new UserEntity();
						Iterator<Cell> cells=row.iterator();
						int cid=0;
						while(cells.hasNext())
						{
						
						Cell cell=cells.next();
						
						switch (cid) {
						case 0:
							entity.setId((long)cell.getNumericCellValue());
							
							break;
						case 1: 
							entity.setName(cell.getStringCellValue());
							break;
							
						case 2:
							entity.setCity(cell.getStringCellValue());
							break;
							
							
						case 3:
							entity.setEmail(cell.getStringCellValue());
							break;
							
							
						case 4:
							entity.setPassword(cell.getStringCellValue());
							break;
							
				
						case 5:
							entity.setMobileNo(cell.getStringCellValue());
							break;
							
						case 6: 
							entity.setState(cell.getStringCellValue());
							break;

						default:
							break;
						}
					
						cid++;
						
					}
			
						
						list.add(entity);
						
			}
			
		
			}catch (Exception e) {
				throw new Exception(" file data not stored");
			}
			return list;
		
		
	}

	
}
