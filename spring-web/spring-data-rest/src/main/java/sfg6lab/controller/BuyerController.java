//: sfg6lab.controller.BuyerController.java

package sfg6lab.controller;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sfg6lab.controller.data.BuyerDto;
import sfg6lab.controller.data.DataMaper;
import sfg6lab.domain.model.Buyer;
import sfg6lab.domain.repository.BuyerRepository;

import java.util.List;


@RepositoryRestController
@RequiredArgsConstructor
class BuyerController {

    private final DataMaper<Buyer, BuyerDto> buyerMapper;
    private final BuyerRepository buyerRepository;

    @GetMapping("/buyers")
    public ResponseEntity<List<BuyerDto>> getAllBuyers() {

        List<Buyer> allBuyers = this.buyerRepository.findAll();

        if (allBuyers == null) {
            return ResponseEntity.notFound().build();
        }

        List<BuyerDto> data = allBuyers.stream().map(buyerMapper::toDto).toList();

        return ResponseEntity.ok(data);
    }

    @GetMapping("/buyers/{id}")
    public ResponseEntity<BuyerDto> getBuyer(@PathVariable Long id) {

        return ResponseEntity.of(this.buyerRepository.findById(id)
                .map(buyerMapper::toDto));
    }

    @PostMapping("/buyers")
    public ResponseEntity<?> createNewBuyer(
            @RequestBody @NonNull final BuyerDto buyerDto) {

        Buyer newBuyer = buyerRepository.save(buyerMapper.toEntity(buyerDto));

        return ResponseEntity.ok(buyerMapper.toDto(newBuyer));
    }

} ///:~