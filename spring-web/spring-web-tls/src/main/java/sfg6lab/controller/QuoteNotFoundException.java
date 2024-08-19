//: sfg6lab.controller.QuoteNotFoundException.java

package sfg6lab.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "There is no such quote.")
public class QuoteNotFoundException extends RuntimeException {

} ///:~