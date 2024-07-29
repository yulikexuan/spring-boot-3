//: sfg6lab.repository.CustomerRepository.java

package sfg6lab.repository;


import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.util.Streamable;
import sfg6lab.domain.model.Customer;


public interface CustomerRepository extends
        ListCrudRepository<Customer, Long>,
        ListPagingAndSortingRepository<Customer, Long> {

    Streamable<Customer> findAllByOrderByIdAsc();
}
