package com.genesys.knowledgecenter.error;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	

	@ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
        return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

	
	@ExceptionHandler({IOException.class,IllegalArgumentException.class,InvalidDocTypeException.class})
	public ResponseEntity<CustomErrorResponse> handleIOException(Exception ex) {
		
		CustomErrorResponse errorResp = new CustomErrorResponse();
		errorResp.setTimestamp(LocalDateTime.now());
		errorResp.setError(ex.getMessage());
		errorResp.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(errorResp,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<CustomErrorResponse> handleFileNotFoundException(Exception ex) {
		
		CustomErrorResponse errorResp = new CustomErrorResponse();
		errorResp.setTimestamp(LocalDateTime.now());
		errorResp.setError(ex.getMessage());
		errorResp.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorResp,HttpStatus.NOT_FOUND);
	} 
	
	@ExceptionHandler(MultipartException.class)
    public ResponseEntity<CustomErrorResponse>  handleError(MultipartException e) {
		CustomErrorResponse errorResp = new CustomErrorResponse();
		errorResp.setTimestamp(LocalDateTime.now());
		errorResp.setError("File size exceeded.");
		errorResp.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(errorResp,HttpStatus.BAD_REQUEST);

    }

}
