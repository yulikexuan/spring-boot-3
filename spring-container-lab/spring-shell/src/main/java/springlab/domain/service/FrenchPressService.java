//: springlab.domain.service.FrenchPressService.java


package springlab.domain.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import springlab.domain.model.Coffee;
import springlab.domain.model.CoffeeBrewingType;
import springlab.service.CoffeeBrewing;

@Slf4j
@Service("frenchPress")
@CoffeeBrewing(CoffeeBrewingType.FRENCH_PRESS)
public class FrenchPressService implements CoffeeBrewService {

    @Override
    public Coffee brew(Coffee coffee) {

        log.debug(">>> Doing coffee french-press ... ");

        return Coffee.brewed(coffee);
    }

}///:~

