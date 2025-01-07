//: sfg6lab.controller.YearMonthRangeController.java

package sfg6lab.controller;


import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import sfg6lab.domain.model.YearMonthRange;

import java.net.URI;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@RestController
@RequestMapping("/yearmonthrange")
class YearMonthRangeController {

// Configuring a global formatter for all controllers is a better solution
//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        binder.addCustomFormatter(new YearMonthRangeFormatter());
//    }

    @GetMapping
    public ResponseEntity<?> getYearMonthRange(
            @RequestParam YearMonthRange yearMonthRange) {

        return ResponseEntity.ok(yearMonthRange);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(
            MethodArgumentTypeMismatchException ex) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                BAD_REQUEST, ex.getMessage());

        problemDetail.setTitle("The format of the argument %s is invalid"
                .formatted(ex.getName()));
        problemDetail.setType(URI.create(
                "https://www.iso.org/iso-8601-date-and-time-format.html"));

        return ResponseEntity.of(problemDetail).build();
    }

} ///:~