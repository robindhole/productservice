package com.codenetworkz.productservice.advice;

import com.codenetworkz.productservice.dtos.ProductResponse;
import com.codenetworkz.productservice.handler.FakeStoreApiException;
import com.codenetworkz.productservice.handler.ProductNotFoundException;
import com.codenetworkz.productservice.handler.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> getProductNotFound(String message){
        ProductResponse response=new ProductResponse();
        response.setErrors(message);
        response.setStatus(HttpStatus.BAD_REQUEST.toString());
        response.setMassage("Hi User There is a problem please try agin");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FakeStoreApiException.class)
    public ResponseEntity<ProductResponse> fakestoreApiCallProblem(String message){
        ProductResponse response=new ProductResponse();
        response.setErrors(message);
        response.setStatus(HttpStatus.BAD_REQUEST.toString());
        response.setMassage("Hi User There is a problem please try agin");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ProductResponse> serviceException(String message){
        ProductResponse response=new ProductResponse();
        response.setErrors(message);
        response.setStatus(HttpStatus.BAD_REQUEST.toString());
        response.setMassage("Hi User There is a problem please try agin");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
