//: sfg6lab.controller.BuyerMatrixController.java

package sfg6lab.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sfg6lab.domain.repository.BuyerRepository;


@Slf4j
@RestController
@RequestMapping("/buyers")
@RequiredArgsConstructor
class BuyerMatrixController {

    private final BuyerRepository buyerRepository;

} /// :~