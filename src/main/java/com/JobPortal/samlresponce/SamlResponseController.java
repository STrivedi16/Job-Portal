//package com.JobPortal.samlresponce;
//
//import java.util.List;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.JobPortal.Config.JwtRefreshToken;
//import com.JobPortal.Config.JwtTokenUtils;
//import com.JobPortal.Model.JwtResponce;
//import com.JobPortal.Responce.ErrorMessage;
//import com.JobPortal.Responce.SuccessMessageToken;
//import com.JobPortal.Service.CustomerUserDetailsSerice;
//
//@RestController
//public class SamlResponseController {
//
//	
//	@Autowired
//	private SamlResponse response;
//	
//	@Autowired
//	private JwtTokenUtils jwtTokenUtils;
//	
//	
//	@Autowired
//	private CustomerUserDetailsSerice customerUserDetailsSerice;
//	
//	@Autowired
//	private JwtRefreshToken jwtRefreshToken;
//	    
//
////	    public class SamlResponseDecoder {
//
//		@PostMapping("/SamlResponse")
//	      public ResponseEntity<?> decodeString(@RequestParam(name = "response", required = false) String response)
//	      {
//			
//			try {
//			
//	    	 String samlUser=this.response.decodeMessage(response);
//	    	 
//	    	 
//	    	
//	    	 
//	    	 System.out.println();
//	    	 
//	    	 UserDetails details=this.customerUserDetailsSerice.loadUserByUsername(samlUser);
//	    	
//	    	 String token=this.jwtTokenUtils.generateToken(details);
//	    	 
//	    	 String reftoken=this.jwtRefreshToken.generateReftoken(details);
//	    	 
//	    	 return new ResponseEntity<>(new SuccessMessageToken("Success", "Success", token, reftoken),HttpStatus.OK);
//	    	 
//			}
//			catch (Exception e) {
//				return new ResponseEntity<>(new ErrorMessage("Error", "Error"),HttpStatus.BAD_REQUEST);
//			}
//	    	  
//	    	  
//	    	  
//	    	  
//	    	  
//	    	  
//	      }
//}
