//: sfg6lab.controller.BuyersControllerAdvise.java

package sfg6lab.controller;


import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@ControllerAdvice(assignableTypes = BuyersController.class)
class BuyersControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<?> handleException(
            InvalidDateTimeRangeArgumentsException ex) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                BAD_REQUEST, ex.getMessage());

        problemDetail.setTitle("Invalid arguments for date time range");
        problemDetail.setType(URI.create("https://docs.oracle.com/en/java" +
                "/javase/21/docs/api/java.base/java/lang/IllegalArgumentException.html"));

        return ResponseEntity.of(problemDetail).build();
    }

} ///:~