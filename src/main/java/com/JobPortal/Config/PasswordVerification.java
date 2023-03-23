package com.JobPortal.Config;

import org.springframework.stereotype.Component;
import java.util.regex.*;

@Component
public class PasswordVerification {

	public boolean verifyPassword(String password)
	{
		Pattern pattern=Pattern.compile("((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})");
		
		Matcher  matcher=pattern.matcher(password);
		
		if(matcher.matches())
		{
			return true;
		}
		else {
			return false;
		}
	}
}
