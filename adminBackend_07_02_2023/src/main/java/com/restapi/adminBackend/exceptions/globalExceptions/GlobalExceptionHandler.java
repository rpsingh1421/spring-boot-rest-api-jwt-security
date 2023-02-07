package com.restapi.adminBackend.exceptions.globalExceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.restapi.adminBackend.Response.Response;
import com.restapi.adminBackend.exceptions.customExceptions.EmptyResourceException;
import com.restapi.adminBackend.exceptions.customExceptions.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Response> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		
		String message = ex.getMessage();
		Response response = new Response(false,message);
		return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);		
	}
	@ExceptionHandler(EmptyResourceException.class)
	public ResponseEntity<Response> emptyResourceExceptionHandler(EmptyResourceException ex){
		String message = ex.getMessage();
		Response response = new Response(false,message);
		return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
	}
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		// TODO Auto-generated method stub
		return new ResponseEntity<Object>("Please change http method type",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<Response> usernameNotFoundExceptionHandler(UsernameNotFoundException ex){
		String message = ex.getMessage();
		Response response = new Response(false,message);
		return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Response> badCredentialsExceptionHandler(BadCredentialsException ex){
		String message = ex.getMessage();
		Response response = new Response(false,message);
		return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
	}
}
