//: springlab.service.CoffeeService.java


package springlab.service;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;
import springlab.domain.model.Coffee;
import springlab.domain.model.CoffeeBrewingType;
import springlab.domain.service.CoffeeBrewService;

import java.util.Map;
import java.util.function.Supplier;


@Service
@RequiredArgsConstructor(
        staticName = "of",
        access = AccessLevel.PACKAGE)
public class CoffeeService {

    private final Supplier<Coffee> coffeeSupplier;

    // @CoffeeBrewing(CoffeeBrewingType.FRENCH_PRESS)
    // private final Map<CoffeeBrewingType, CoffeeBrewService> coffeeBrewServices;
    private final ObjectProvider<Map<CoffeeBrewingType, CoffeeBrewService>>
            coffeeBrewServicesProvider;

    public Coffee serve(CoffeeBrewingType type) {
        var brewServices = coffeeBrewServicesProvider.getIfAvailable();
        if (brewServices != null) {
            return brewServices.get(type).brew(this.coffeeSupplier.get());
        }
        return this.coffeeSupplier.get();
    }

}///:~