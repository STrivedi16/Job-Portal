package com.JobPortal.Exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.JobPortal.Responce.ErrorException;
import com.JobPortal.Responce.ErrorMessage;
import com.JobPortal.Responce.ErrorMessageConstant;
import com.JobPortal.Responce.ErrorMessageKey;
import com.JobPortal.Responce.ResourcesNotFoundException;

@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler{ 

	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		return new ResponseEntity<Object>(new ErrorMessage(ErrorMessageConstant.REQUEST_ERROR, ErrorMessageKey.REQUEST_E0300),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourcesNotFoundException.class)
	public ResponseEntity<?> resourcesNotFound(ResourcesNotFoundException exception,HttpServletRequest request)
	{
		String message=exception.getMessage();
		StringBuffer key=request.getRequestURL();
		
		return new ResponseEntity<>(new ErrorException(message, key),HttpStatus.BAD_REQUEST);
	}
}
