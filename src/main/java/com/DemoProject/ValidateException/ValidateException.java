package com.DemoProject.ValidateException;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.DemoProject.Entity.ApiResponse;

@ControllerAdvice
public class ValidateException {

    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> notValid(MethodArgumentNotValidException ex) {

        Map<String, List<String>> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.computeIfAbsent(fieldName, key -> new ArrayList<>()).add(errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }*/
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<ApiResponse> handleValidationException(MethodArgumentNotValidException ex) {
	        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
	        ApiResponse response = new ApiResponse(errorMessage, null, 400);
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }

	    // 🔹 2️⃣ Handle General Exceptions (Any unhandled errors)
	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ApiResponse> handleException(Exception ex) {
	        ApiResponse response = new ApiResponse("Internal server error: " + ex.getMessage(), null, 500);
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
}

