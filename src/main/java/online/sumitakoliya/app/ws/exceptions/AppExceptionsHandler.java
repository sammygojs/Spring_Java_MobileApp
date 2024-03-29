package online.sumitakoliya.app.ws.exceptions;
import online.sumitakoliya.app.ws.model.response.ErrorMessage;

import java.util.Date;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value= {Exception.class})
	public ResponseEntity<Object>handleAnyException(Exception ex, WebRequest request){
		
		String errorMessageDescription = ex.getLocalizedMessage();
		if(errorMessageDescription==null) errorMessageDescription = ex.toString();
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getLocalizedMessage());
		
		return new ResponseEntity<>(
				errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value= {NullPointerException.class})
	public ResponseEntity<Object>handleAnyException(NullPointerException ex, WebRequest request){
		
		String errorMessageDescription = ex.getLocalizedMessage();
		if(errorMessageDescription==null) errorMessageDescription = ex.toString();
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getLocalizedMessage());
		
		return new ResponseEntity<>(
				errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value= {UserServiceException.class})
	public ResponseEntity<Object>handleUserServiceException(UserServiceException ex, WebRequest request){
		
		String errorMessageDescription = ex.getLocalizedMessage();
		if(errorMessageDescription==null) errorMessageDescription = ex.toString();
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getLocalizedMessage());
		
		return new ResponseEntity<>(
				errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
