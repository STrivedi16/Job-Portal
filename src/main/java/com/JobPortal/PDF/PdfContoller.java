package com.JobPortal.PDF;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.netflix.discovery.converters.Auto;

@RestController
public class PdfContoller {

	@Autowired
	private DataMapper  dataMapper;
	
	@Autowired
	private SpringTemplateEngine engine;
	
	@Autowired
	private PdfService pdfService;
	
	@Value("${pdf.template}")
	private String template;
	
	@GetMapping("/generatePdf")
	public void generatePdf()
	{
		String html=null;
		
		Context context=dataMapper.setData();
		
		html=engine.process("Cirtificate", context);
		
		this.pdfService.generatePdf(html);
		
		
	}
}
