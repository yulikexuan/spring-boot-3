//: sfg6lab.controller.data.BuyerMapper.java

package sfg6lab.controller.data;


import lombok.NonNull;
import sfg6lab.domain.model.Buyer;

import java.util.Set;


public class BuyerMapper implements DataMaper<Buyer, BuyerDto> {

    @Override
    public BuyerDto toDto(@NonNull final Buyer entity) {

        return new BuyerDto(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getLevel(),
                entity.isActive(),
                entity.getCreated());
    }

    @Override
    public Buyer toEntity(BuyerDto dto) {

        return new Buyer(
                dto.id(),
                null,
                dto.name(),
                dto.email(),
                dto.level(),
                dto.active(),
                dto.created(),
                Set.of());
    }

} ///:~