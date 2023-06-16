package com.JobPortal.Config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.JobPortal.Repository.UserRepository;
import com.JobPortal.entity.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtils implements Serializable{

	

	private static final long serialVersionUID = -2550185165626007488L;

	private static final long JWT_TOEKN_VALIDITY = 5 * 60 * 60;

	private static final String TYPE = "Access";
	
	@Autowired
	private UserRepository repository;

//	private String secret = "Java@2711";

	@Value("${jwt.secret}")
	private String secret;
	
	public String getUsernameFromToken(String token)
	{
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	public Date getExpirationdateFromToken(String token)
	{
		return getClaimFromToken(token,Claims::getExpiration);
	}
	
	public <T> T getClaimFromToken(String token,Function<Claims, T>claimResover)
	{
		final Claims claims=getAllClaimFromToken(token);
		
		return  claimResover.apply(claims);
		
	}
	
	private Claims getAllClaimFromToken(String token)
	{
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	public String getTypeFromToken(String token)
	{
		Claims claims=Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		
		return (String)claims.get("Type");
	}
	
	public String getUserEmailFromToken(String token)
	{
		Claims claims=Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		
		return (String)claims.get("email");
	}
	
	public long getUserIdFromToken(String token)
	{
		Claims claims=Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		
		return (long)claims.get("id");
	}
	
	public boolean isTokenExpired(String token)
	{
		final Date date=getExpirationdateFromToken(token);
		
		return date.before(new Date());
	}
	
	
	public String generateToken(UserDetails details)
	{
		Map<String , Object> map=new HashMap<>();
		
		
		
		map.put("Type", TYPE);
		
		
		return dogenerate(map,details.getUsername());
		
	}
	
	
	public String generateRedisToken(UserEntity details)
	{
		Map<String , Object> map=new HashMap<>();
		map.put("email", details.getEmail());
		map.put("id", details.getId());
		
		
		System.err.println("generating redis token");
		
		return dogenerate(map, details.getEmail());
	}
	
	private String dogenerate(Map<String, Object>claim)
	{
		
	
		
		return Jwts.builder().setClaims(claim).signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	private String dogenerate(Map<String, Object>claim,String objcet)
	{
		return Jwts.builder().setClaims(claim).setSubject(objcet).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+JWT_TOEKN_VALIDITY*1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
				
	}
	
	// Check token is validate 
	public boolean isTokenValidate(String token, UserDetails details)
	{
		final String username=getUsernameFromToken(token);
		
		return (username.equals(details.getUsername())|| !isTokenExpired(token));
		
	}
	
	
	
}
