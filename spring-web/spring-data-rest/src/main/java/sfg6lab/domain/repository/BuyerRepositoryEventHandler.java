//: sfg6lab.domain.repository.BuyerRepositoryEventHandler.java

package sfg6lab.domain.repository;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;
import sfg6lab.domain.model.Buyer;


@Slf4j
@Component
@RepositoryEventHandler(BuyerRepository.class)
class BuyerRepositoryEventHandler {

    @HandleBeforeCreate
    public void handleBuyerBeforeCreate(@NonNull final Buyer buyer) {
        log.info(">>> Will create a new Buyer: {}", buyer);
    }

} ///:~