//: sfg6lab.controller.HeadersController.java

package sfg6lab.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;


@RestController
@RequestMapping("/headers")
@RequiredArgsConstructor
class HeadersController {

    @GetMapping("/languages/ranges")
    public ResponseEntity<List<Locale.LanguageRange>> allLanguageRanges(
            @RequestHeader HttpHeaders headers) {
        return ResponseEntity.ok(headers.getAcceptLanguage());
    }

    @GetMapping("/locales")
    public ResponseEntity<?> allAcceptLocales(
            @RequestHeader HttpHeaders headers) {

        return ResponseEntity.ok(headers.getAcceptLanguageAsLocales());
    }

} ///:~