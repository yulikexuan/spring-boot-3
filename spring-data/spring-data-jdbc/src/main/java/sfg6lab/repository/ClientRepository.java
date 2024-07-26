//: sfg6lab.repository.ClientRepository.java

package sfg6lab.repository;


import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import sfg6lab.domain.model.Client;

import java.util.Optional;


public interface ClientRepository extends
        ListCrudRepository<Client, Long>,
        ListPagingAndSortingRepository<Client, Long> {

    Optional<Client> findByName(String name);

    Optional<Client> findByEmail(String email);
}