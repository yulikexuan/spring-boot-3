//: sfg6lab.domain.repository.BuyerProjection.java

package sfg6lab.domain.repository;


import org.springframework.data.rest.core.config.Projection;
import sfg6lab.domain.model.Buyer;

import java.time.LocalDateTime;


@Projection(name = "dto", types = {Buyer.class})
interface BuyerProjection {

    String getName();

    String getEmail();

    int getLevel();

    boolean isActive();

    LocalDateTime getCreated();

    // Set<BuyerContact> getContacts();

} ///:~