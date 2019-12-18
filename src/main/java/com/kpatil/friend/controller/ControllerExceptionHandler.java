package com.kpatil.friend.controller;

import com.kpatil.friend.util.ErrorMessage;
import javax.xml.bind.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ValidationException.class)
  ErrorMessage exceptionHandler(ValidationException e) {
    return new ErrorMessage(String.valueOf(HttpStatus.BAD_REQUEST.value()), e.getMessage());
  }
}
