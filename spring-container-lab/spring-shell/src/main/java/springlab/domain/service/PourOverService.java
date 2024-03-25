//: springlab.domain.service.PourOverService.java


package springlab.domain.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import springlab.domain.model.Coffee;
import springlab.domain.model.CoffeeBrewingType;
import springlab.service.CoffeeBrewing;


@Slf4j
@Service("pourOver")
// @Primary
@CoffeeBrewing(CoffeeBrewingType.POUR_OVER)
public class PourOverService implements CoffeeBrewService {

    @Override
    public Coffee brew(Coffee coffee) {

        log.debug(">>> Doing coffee pour-over ... ");

        return Coffee.brewed(coffee);
    }

}///:~