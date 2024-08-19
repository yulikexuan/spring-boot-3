//: sfg6lab.controller.DateTimeRangeController.java

package sfg6lab.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sfg6lab.domain.model.DateTimeRange;


@RestController
@RequestMapping("/datetime/range")
class DateTimeRangeController {

    static final String RESULT_TEMPLATE = "%s%n%s";

    @GetMapping
    public ResponseEntity<?> getDateTimeRange(
            @ModelAttribute DateTimeRange range, BindingResult bindingResult) {

        return ResponseEntity.ok(range);
    }

} ///:~