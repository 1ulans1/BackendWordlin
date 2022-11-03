//package com.wordlin.backendwordlin.exeption;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//
//@ControllerAdvice
//public class ExceptionsHandler {
//
//
//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<?> handleUserAlreadyExists(
//            UserNotFoundException e, WebRequest request) {
//
//        CustomErrorMessage body = new CustomErrorMessage(
//                HttpStatus.BAD_REQUEST.value(),
//                HttpStatus.BAD_REQUEST.getReasonPhrase(),
//                "User exist!",
//                "/api/empl/payment");
//
//        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(UsernameNotFoundException.class)
//    public ResponseEntity<CustomErrorMessage> handleUserNotFound(
//            UserAlreadyExistsException e, WebRequest request) {
//
//        CustomErrorMessage body = new CustomErrorMessage(
//                401,
//                "Unauthorized",
//                "",
//                "/api/empl/payment");
//
//        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
//    }
//}
