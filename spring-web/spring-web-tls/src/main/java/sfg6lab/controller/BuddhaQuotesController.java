//: sfg6lab.controller.BuddhaQuotesController.java

package sfg6lab.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;


@Slf4j
@RestController
class BuddhaQuotesController {

    private static final String[] QUOTES = {
            "Peace comes from within. Do not seek it without.",
            "Better than a thousand hollow words is one word that brings peace.",
            "Hatred does not cease by hatred, but only by love; this is the eternal rule.",
            "Those who are free of resentful thoughts surely find peace.",
            "If you truly loved yourself, you could never hurt another.",
            "The greatest prayer is patience.",
            "Peace comes from understanding and accepting that which is." };

    @GetMapping("/buddha-quotes")
    public ResponseEntity<?> retrieveQuoteByIndex(int index) {

//        try {
//            return ResponseEntity.ok(QUOTES[index]);
//        } catch (Exception e) {
//            throw new QuoteNotFoundException();
//        }

        return ResponseEntity.ok(QUOTES[index]);
    }

    // @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such quote.")
    @ExceptionHandler
    public ResponseEntity<?> noSuchQuote(IndexOutOfBoundsException e) {

        log.debug(">>> Handling {}", e.getClass().getSimpleName());

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, e.getMessage());

        problemDetail.setTitle("No such quote.");
        problemDetail.setType(URI.create("https://docs.oracle.com/en/java" +
                "/javase/21/docs/api/java.base/java/lang/IndexOutOfBoundsException.html"));

        return ResponseEntity.of(problemDetail).build();
    }



} ///:~