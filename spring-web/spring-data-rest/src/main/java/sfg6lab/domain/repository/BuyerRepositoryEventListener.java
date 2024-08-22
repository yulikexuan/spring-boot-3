//: sfg6lab.domain.repository.BuyerRepositoryEventListener.java

package sfg6lab.domain.repository;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.relational.core.mapping.event.AbstractRelationalEventListener;
import org.springframework.data.relational.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;
import sfg6lab.domain.model.Buyer;


@Slf4j
@Component
public class BuyerRepositoryEventListener
        extends AbstractRelationalEventListener<Buyer> {

    @Override
    public void onAfterSave(AfterSaveEvent<Buyer> event) {
        log.info(">>> A new Buyer has been saved in the database: {}",
                event.getEntity());
    }

} ///:~