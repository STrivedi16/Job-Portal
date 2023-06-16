package com.JobPortal.FileHandling;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.JobPortal.Repository.RoleRepository;
import com.JobPortal.Repository.UserCsvRepo;
import com.JobPortal.Repository.UserRepository;
import com.JobPortal.Repository.UserRoleRepository;
import com.JobPortal.entity.Permissions;
import com.JobPortal.entity.Roles;
import com.JobPortal.entity.Skills;
import com.JobPortal.entity.UserCsv;
import com.JobPortal.entity.UserEntity;
import com.JobPortal.entity.UserRolesEntity;
import com.netflix.discovery.converters.Auto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Controller;

import java.io.InputStreamReader;
import java.io.Reader;

@Component
public class FileService {


	@Autowired
	private BCryptPasswordEncoder  encoder;
	
	
		public  boolean checkFileFormat(MultipartFile file) {
			if (file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
				return true;
			} else {
				return false;
			}
		}

		@Async
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
		
		@Async
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
	
		
		@Autowired
		private UserRepository repository;
		
		@Autowired
		private RoleRepository  roleRepository;
		
		@Autowired
		private UserRoleRepository userRoleRepository;
 				
		public void setDataInMultitable(MultipartFile file) throws Exception
		{
			List<UserEntity> user=new ArrayList<>();
			
			List<Roles> lRoles=new ArrayList<>();
			List<Permissions> lPermission=new ArrayList<>();
			
			try {
			
				XSSFWorkbook workbook= new XSSFWorkbook(file.getInputStream());
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
					
					
					Iterator<Cell> cells=row.iterator();
					int cid=0;
					UserEntity entity=new UserEntity();
					Roles role=new Roles();
					
					while(cells.hasNext())
					{
						Cell cell=cells.next();
						
						switch (cid) {
						case 0:
							entity.setName(cell.getStringCellValue());
							break;
							
						case 1:
							entity.setCity(cell.getStringCellValue());
							break;
						case 2:
							String email=cell.getStringCellValue();
							if(email.isEmpty())
							{
								throw new Exception();
							}
							
							entity.setEmail(email);
							break;
						case 3:
							
							String password=encoder.encode(cell.getStringCellValue());
							entity.setPassword(password);
							break;
							
					
						case 4:
							entity.setState(cell.getStringCellValue());
							break;	
							
						case 5:
							role.setRoles(cell.getStringCellValue());
							break;

						default:
							break;
						}
						cid++;
						
						
						
					}
					
					user.add(entity);
					lRoles.add(role);
					
					List<UserEntity> entities=this.repository.saveAll(user);
					List<Roles> roles=this.roleRepository.saveAll(lRoles);
					long uId = 0 ,rId = 0;
					for(int i=0;i<entities.size()&&i<roles.size();i++)
					{
						for(UserEntity users: entities)
						{
							uId=users.getId();
						}
						
						for(Roles uRoles :roles)
						{
							rId=uRoles.getId();
						}
						
						UserEntity entity2=this.repository.findById(uId).orElseThrow(()-> new Exception("User not found"));
						
						Roles roles2=this.roleRepository.findById(rId).orElseThrow(()-> new Exception("Roles not found"));
						
						UserRolesEntity  eUserRolesEntity=new UserRolesEntity();
						eUserRolesEntity.setUsers(entity2);
						eUserRolesEntity.setRoles(roles2);
						
						this.userRoleRepository.save(eUserRolesEntity);
						
					}
					
					
				}
			}catch (Exception e) {
				e.printStackTrace();
				throw new Exception("data not stored");
			}
			
			
			
			
		}
}
