package com.JobPortal.Config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.JobPortal.Repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtRefreshToken implements Serializable{

	private static final long serialVersionUID = -2550185165626007488L;

	private static final long JWT_TOEKN_VALIDITY = 25 * 60 * 60;

	private static final String TYPE = "Refresh";
	
	@Autowired
	private UserRepository repository;

//	private String secret = "Java@2711";

	@Value("${jwt.secret}")
	private String secret;
	
	public String getUsernameFromReftoken(String reftoken)
	{
		return getClaimFromReftoken(reftoken,Claims::getSubject);
				
	}
	
	public Date getExpirationDateFromReftoken(String reftoken)
	{
		return getClaimFromReftoken(reftoken, Claims::getExpiration);
	}
	
	public <T> T getClaimFromReftoken(String reftoken, Function<Claims, T>claimResolver)
	{
		final Claims claims=getAllClaimFromReftoken(reftoken);
		
	return 	claimResolver.apply(claims);
	}
	
	
	private Claims getAllClaimFromReftoken(String refToken)
	{
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(refToken).getBody();
		
	}
	
	private String getTypeFromReftoken(String reftoken)
	{
		final Claims claims=Jwts.parser().setSigningKey(secret).parseClaimsJws(reftoken).getBody();
		
		return (String )claims.get("Type");
		
	}
	
	
	public boolean isReftokenExcpired(String reftoken)
	{
		Date date=getExpirationDateFromReftoken(reftoken);
		
		return date.before(new Date());
	}
	
	public String generateReftoken(UserDetails details)
	{
		Map<String , Object> map=new HashMap<>();
		
		map.put("Type", TYPE);
		
		return dogenerate(map,details.getUsername());
		
		
		
	}
	
	
	public String dogenerate(Map<String, Object>claims,String object )
	{
		return Jwts.builder().setClaims(claims).setSubject(object).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+JWT_TOEKN_VALIDITY*1000)).signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}
	
}
