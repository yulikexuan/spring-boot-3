//: sfg6lab.repository.DeliveryRepository.java

package sfg6lab.repository;


import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import sfg6lab.domain.model.Delivery;


public interface DeliveryRepository extends
        ListCrudRepository<Delivery, Long>,
        ListPagingAndSortingRepository<Delivery, Long> {

    /*
     * This method needs to be annotated with @Query, as customerId does not
     * exist in the Address class
     */
    @Query("SELECT COUNT(*) FROM deliveries WHERE customer_id = :customerId")
    int countByCustomerId(@Param("customerId") Long customerId);
}
