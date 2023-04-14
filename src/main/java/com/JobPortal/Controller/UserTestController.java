//package com.JobPortal.Controller;
//
//import javax.ws.rs.POST;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.JobPortal.Service.UsertestService;
//import com.JobPortal.entity.UserEntity;
//import com.JobPortal.entity.UserTest;
//import com.netflix.discovery.converters.Auto;
//
//@RestController
//public class UserTestController {
//
//	@Autowired
//	private UsertestService service;
//	
//	@PostMapping("/usertest")
//	public UserTest setdata(@RequestBody UserTest  test)
//	{
//		return this.service.setData(test);
//	}
//	
//	@GetMapping("/usertest/{id}")
//	public UserTest getData(@PathVariable("id") int id )
//	{
//		return this.service.getdata(id);
//	}
//}
