//: sfg6lab.repository.BuyerContactManyToManyRepository.java

package sfg6lab.repository;


import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import sfg6lab.domain.model.BuyerContact;


public interface BuyerContactManyToManyRepository extends
        ListCrudRepository<BuyerContact, Long>,
        ListPagingAndSortingRepository<BuyerContact, Long> {

    @Query("SELECT COUNT(*) FROM buyers_contacts WHERE buyer_id = :buyerId")
    int countByBuyerId(@Param("buyerId") Long buyerId);
}
