package com.JobPortal.OTP;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.JobPortal.Responce.ResourcesNotFoundException;

@Service
public class EmailService {

	@Value("${spring.mail.host}")
	private String host;
	
	@Value("${spring.mail.port}")
	private int port;
	
	public boolean sendOtp(String subject, String message, String to) throws MessagingException
	{	
		boolean f=false;
		
		Properties properties=System.getProperties();
		
		String from="shubhamtrivedi@nimapinfotech.com";
		
		// set intformation to properties
		System.out.println("properties "+ properties);
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		
		//get session object
		Session session=Session.getInstance(properties, new Authenticator() {
		
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			
			return new PasswordAuthentication("shubhamtrivedi@nimapinfotech.com", "shubhamT2711$");
		}
		
		});
		
		//step 2: compose the message
		MimeMessage message2=new MimeMessage(session);
		MimeMessageHelper helper=new MimeMessageHelper(message2);
		
		
		try {
			message2.setFrom(from);
			message2.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message2.setSubject(subject);
			message2.setContent(message,"text/html");
		
			
			// step 3: send message using transport class
			Transport.send(message2);
			
			f=true;
		}
		catch (ResourcesNotFoundException e) {
			e.printStackTrace();
		}
		
		return f;
		
	}
	
}
