package com.JobPortal.PDF;

import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Service
public class DataMapper {

	
	public Context setData()
	{
		Context context=new Context();
		
		context.setVariable("username", "Shubham Trivedi");
		
		return context;
		
	}
}
