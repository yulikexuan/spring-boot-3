//: sfg6lab.repository.AddressRepository.java

package sfg6lab.repository;


import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import sfg6lab.domain.model.Address;


public interface AddressRepository extends
        ListCrudRepository<Address, Long>,
        ListPagingAndSortingRepository<Address, Long> {

}
