package com.votingapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex){
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getMessage(),false),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UsernameAlreadyExistException.class)
	public ResponseEntity<ErrorResponse> handleUsernameAlreadyExistException(UsernameAlreadyExistException ex){
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getMessage(),false),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmailAlreadyExistException.class)
	public ResponseEntity<ErrorResponse> handleEmailAlreadyExistException(EmailAlreadyExistException ex){
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getMessage(),false),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PhoneNumberAlreadyExistException.class)
	public ResponseEntity<ErrorResponse> handlePhoneNumberAlreadyExistException(PhoneNumberAlreadyExistException ex){
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getMessage(),false),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException ex){
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getMessage(),false),HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(InvalidOperationException.class)
	public ResponseEntity<ErrorResponse> handleInvalidOperationException(InvalidOperationException ex){
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getMessage(),false),HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(CandidateNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleCandidateNotFoundException(CandidateNotFoundException ex){
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getMessage(),false),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ElectionNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleElectionNotFoundException(ElectionNotFoundException ex){
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getMessage(),false),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(VoterNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleVoterNotFoundException(VoterNotFoundException ex){
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getMessage(),false),HttpStatus.NOT_FOUND);
	}
	
	

}
