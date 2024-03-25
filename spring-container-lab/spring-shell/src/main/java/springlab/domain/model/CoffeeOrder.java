//: springlab.domain.model.CoffeeOrder


package springlab.domain.model;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;


public interface CoffeeOrder extends Ordered, AutoCloseable {

    CoffeeBrewingType brewingType();

    @Override
    default int getOrder() {

        if (this.brewingType() == CoffeeBrewingType.POUR_OVER) {
            return 1;
        } else {
            return 50;
        }

    }

    static CoffeeOrder of(CoffeeBrewingType brewingType) {
        return new CoffeeOrderImpl(brewingType);
    }

}

@Slf4j
record CoffeeOrderImpl(CoffeeBrewingType brewingType) implements CoffeeOrder {

    public void close() {
        log.info(">>> Will close coffee order {}", this);
    }

}

///:~