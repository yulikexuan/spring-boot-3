//: sfg6lab.repository.BuyerManyToManyRepository.java

package sfg6lab.domain.repository;


import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import sfg6lab.domain.model.Buyer;

import java.time.LocalDateTime;
import java.util.List;


public interface BuyerRepository extends
        ListCrudRepository<Buyer, Long>,
        ListPagingAndSortingRepository<Buyer, Long> {

    List<Buyer> findAllByCreatedBetweenOrderByCreated(LocalDateTime from, LocalDateTime to);

    @Query("SELECT DISTINCT created FROM buyers")
    List<LocalDateTime> findAllCreatedDates();

}
