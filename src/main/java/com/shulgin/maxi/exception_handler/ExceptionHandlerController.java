package com.shulgin.maxi.exception_handler;

import com.shulgin.maxi.exception.IncorrectInputData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler
    public ResponseEntity<IncorrectInputData> exceptionHandler(Exception e) {
        IncorrectInputData incorrectInputData = new IncorrectInputData();
        incorrectInputData.setInfo(e.getMessage());
        return new ResponseEntity<>(incorrectInputData, HttpStatus.NOT_FOUND);
    }
}
