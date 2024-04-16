//: sfg6lab.domain.service.NumberService.java


package sfg6lab.domain.service;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PACKAGE)
class NumberService {

    private final List<Number> numbers;

    List<? super Number> prepareInput() {
        return numbers;
    }

    List<? extends Number> prepareOutput() {
        return numbers;
    }

    List<Number> numbers() {
        return numbers;
    }

}///:~