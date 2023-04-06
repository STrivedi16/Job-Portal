package com.JobPortal.PDF;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

import org.springframework.stereotype.Service;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.kernel.pdf.PdfWriter;

@Service
public class PdfService {

	
	public String generatePdf(String html)
	{
		ByteArrayOutputStream arrayOutputStream=new ByteArrayOutputStream();
		
		try {
			PdfWriter pdfWriter=new PdfWriter(arrayOutputStream);
			
			DefaultFontProvider defaultFontProvider=new DefaultFontProvider(false, true, false);// public DefaultFontProvider(boolean registerStandardPdfFonts, boolean registerShippedFonts,
           // boolean registerSystemFonts) 
			
			ConverterProperties converterProperties=new ConverterProperties();
			
			converterProperties.setFontProvider(defaultFontProvider);
			
			HtmlConverter.convertToPdf(html, pdfWriter, converterProperties);
			
			FileOutputStream fout = new  FileOutputStream("/Users/hp/Desktop/pdf/user.pdf");
			
			arrayOutputStream.writeTo(fout);
			arrayOutputStream.close();
			arrayOutputStream.flush();
			
			fout.close();
			
			return null;
			
			
			
		}
		catch (Exception e) {
			return null;
		}
	}
}
