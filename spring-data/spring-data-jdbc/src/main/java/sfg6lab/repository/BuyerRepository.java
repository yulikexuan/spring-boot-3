//: sfg6lab.repository.BuyerManyToManyRepository.java

package sfg6lab.repository;


import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import sfg6lab.domain.model.Buyer;


public interface BuyerRepository extends
        ListCrudRepository<Buyer, Long>,
        ListPagingAndSortingRepository<Buyer, Long> {

}
