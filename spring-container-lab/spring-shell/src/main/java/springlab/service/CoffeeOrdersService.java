//: springlab.service.CoffeeOrdersService.java


package springlab.service;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springlab.domain.model.CoffeeOrder;

import java.util.List;


@Service
@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PACKAGE)
public class CoffeeOrdersService {

    private final List<CoffeeOrder> coffeeOrders;

    public List<CoffeeOrder> coffeeOrders() {
        return this.coffeeOrders;
    }

}///:~