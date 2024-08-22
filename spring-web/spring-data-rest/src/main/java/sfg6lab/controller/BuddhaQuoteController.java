//: sfg6lab.controller.BuddhaQuoteController.java

package sfg6lab.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sfg6lab.domain.service.QuoteService;


@RestController
@RequiredArgsConstructor
class BuddhaQuoteController {

    private final QuoteService buddhaQuoteService;

    @GetMapping("/quotes/{index}")
    public ResponseEntity<?> getQuote(@PathVariable int index) {

        return ResponseEntity.ok(buddhaQuoteService.quote(index));
    }

} ///:~