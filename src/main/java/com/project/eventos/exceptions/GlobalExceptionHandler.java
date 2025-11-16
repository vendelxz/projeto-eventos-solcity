package com.project.eventos.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
          ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
    });
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

}

@ExceptionHandler(EntityNotFoundException.class)
public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex){
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);

}

@ExceptionHandler(DataIntegrityViolationException.class)
public ResponseEntity<String> handleDataIntegrityException(DataIntegrityViolationException ex){
    return new ResponseEntity<>("JSON Inválido ou formatado de forma errada", HttpStatus.BAD_REQUEST);

}

@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
public ResponseEntity<String> handleMethotNotSupported(HttpRequestMethodNotSupportedException ex){
return new ResponseEntity<>("Método não suportado: "+ ex.getMethod(), HttpStatus.METHOD_NOT_ALLOWED);

}

//Esse daqui é para pegar todas as outras exceções gerais, eu quero debugar elas no console 
@ExceptionHandler(Exception.class)
public ResponseEntity<String> handleAllUncaughtExceptions(Exception ex){
    ex.printStackTrace(); //Aqui ela vai mostrar pra gente no log o problema...

    return new ResponseEntity<>("Erro interno no servidor", HttpStatus.INTERNAL_SERVER_ERROR);

}

@ExceptionHandler(RuntimeException.class)
public ResponseEntity<String> handleRuntimeExceptions(RuntimeException ex){
    return new ResponseEntity<>("Erro interno no servidor", HttpStatus.INTERNAL_SERVER_ERROR);

}

@ExceptionHandler(EmailExistsException.class)
public ResponseEntity<Map<String, String>> handleEmailExistsException(EmailExistsException ex){
    Map<String, String> errors = new HashMap<>();
    errors.put("errors", ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

}


















}