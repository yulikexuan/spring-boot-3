//: sfg6lab.controller.data.BuyerMapper.java

package sfg6lab.controller.data;


import lombok.NonNull;
import sfg6lab.domain.model.Buyer;

import java.util.Set;


public class BuyerMapper implements DataMaper<Buyer, BuyerDto> {

    @Override
    public BuyerDto toDto(@NonNull final Buyer entity) {

        return new BuyerDto(
                entity.id(),
                entity.name(),
                entity.email(),
                entity.level(),
                entity.active(),
                entity.created());
    }

    @Override
    public Buyer toEntity(BuyerDto dto) {

        return new Buyer(
                dto.id(),
                dto.name(),
                dto.email(),
                dto.level(),
                dto.active(),
                dto.created(),
                Set.of());
    }

} ///:~