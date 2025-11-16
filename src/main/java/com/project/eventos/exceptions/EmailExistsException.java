package com.project.eventos.exceptions;


public class EmailExistsException extends RuntimeException {
    public EmailExistsException (String msg){
        super(msg);
    }

}
