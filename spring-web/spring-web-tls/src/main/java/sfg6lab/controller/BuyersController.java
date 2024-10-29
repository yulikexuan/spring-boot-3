//: sfg6lab.controller.BuyersController.java

package sfg6lab.controller;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sfg6lab.controller.data.BuyerDto;
import sfg6lab.controller.data.DataMaper;
import sfg6lab.domain.model.Buyer;
import sfg6lab.domain.model.BuyerContact;
import sfg6lab.domain.repository.BuyerRepository;
import sfg6lab.domain.repository.ContactRepository;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;


@Slf4j
@RestController
@RequestMapping("/buyers")
@RequiredArgsConstructor
class BuyersController {

    private final BuyerRepository buyerRepository;
    private final ContactRepository contactRepository;

    private final DataMaper<Buyer, BuyerDto> buyerMapper;

    @PostMapping
    public ResponseEntity<?> createBuyer(
            @RequestBody @NonNull final BuyerDto buyerDto,
            ServletUriComponentsBuilder uriBuilder) {

        Buyer buyer = this.buyerMapper.toEntity(buyerDto);
        buyerRepository.save(buyer);

        URI newBuyerUri =  uriBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(buyer.id())
                .toUri();

        return ResponseEntity.created(newBuyerUri).body(buyerDto);
    }

    @GetMapping("/count")
    public String getAmountOfBuyers() {
        long count = buyerRepository.count();
        return ">>> Have totally %d buyer(s)".formatted(count);
    }

    /*
     * The specification of produces = MediaType.APPLICATION_JSON_VALUE is
     * unnecessary :
     * If the content type is not set to String, byte[], or Resource,
     * the object is converted to a JSON output by default
     * Internally, this is handled by the Jackson library
     */
    @GetMapping(path = "/all") //, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BuyerDto>> getAllBuyers() {

        return ResponseEntity.ok(buyerRepository.findAll().stream()
                .map(buyerMapper::toDto).toList());
    }

    /*
     * The specification of produces = MediaType.APPLICATION_JSON_VALUE is
     * unnecessary :
     * If the content type is not set to String, byte[], or Resource,
     * the object is converted to a JSON output by default
     * Internally, this is handled by the Jackson library
     */
    @GetMapping(path = "/all/created-time")
    public ResponseEntity<List<Buyer>> getBuyersByCreatedTimeBetween(
            @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Optional<LocalDateTime> from,
            @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Optional<LocalDateTime> to) {

        LocalDateTime fromTime = from.orElse(LocalDateTime.of(
                2020, 1, 1, 0, 0));

        LocalDateTime toTime = to.orElse(LocalDateTime.of(
                2021, 12, 31, 0, 0));

        if (toTime.isBefore(fromTime)) {
            var errMsg =
                    "The 'to' argument %s should be after the 'from' argument %s. "
                    .formatted(toTime, fromTime);

            throw InvalidDateTimeRangeArgumentsException.of(
                    fromTime, toTime, errMsg);
        }

        List<Buyer> buyers = buyerRepository.findAllByCreatedBetweenOrderByCreated(
                fromTime, toTime);

        log.debug(">>> Found {} buyers.", buyers.size());

        return ResponseEntity.ok(buyers);
    }

    /*
     * The specification of produces = MediaType.APPLICATION_JSON_VALUE is
     * unnecessary :
     * If the content type is not set to String, byte[], or Resource,
     * the object is converted to a JSON output by default
     * Internally, this is handled by the Jackson library
     */
    @GetMapping(path = "/all/created") // , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LocalDateTime>> getAllCreatedTimes() {

        return ResponseEntity.ok(this.buyerRepository.findAllCreatedDates());
    }

    /*
     * The specification of produces = MediaType.APPLICATION_JSON_VALUE is
     * unnecessary :
     * If the content type is not set to String, byte[], or Resource,
     * the object is converted to a JSON output by default
     * Internally, this is handled by the Jackson library
     */
    @GetMapping(path = "/{id}") // , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBuyerById(@PathVariable("id") Optional<Buyer> maybeBuyer) {
        return ResponseEntity.of(maybeBuyer.map(buyerMapper::toDto));
    }

    @GetMapping(path = "/{id}/contacts/{contackId}")
    public ResponseEntity<?> getOneOfTheContactsOfBuyer(
            @PathVariable("id") Long buyerId,
            @PathVariable("contackId") Long contactId) {

        return ResponseEntity.of(buyerRepository.findById(buyerId)
                .flatMap(buyer -> buyer.contacts().stream()
                        .filter(bc -> bc.contactId().equals(contactId)).findFirst())
                .map(BuyerContact::contactId)
                .map(contactRepository::findById));
    }

    @GetMapping("/all/stream")
    public ResponseEntity<Stream<BuyerDto>> getAllBuyers(
            @PageableDefault(page = 0, size = 3, sort = "created", direction = ASC)
            Pageable pageable) {

        return ResponseEntity.ok(buyerRepository.findAll(pageable)
                .map(buyerMapper::toDto)
                .get());
    }

    @GetMapping("/all/page")
    public ResponseEntity<Page<BuyerDto>> getBuyerPage(
            @PageableDefault(page = 0, size = 3, sort = "created", direction = DESC)
            Pageable pageable) {

        return ResponseEntity.ok(buyerRepository
                .findAll(pageable)
                .map(buyerMapper::toDto));
    }

} ///:~