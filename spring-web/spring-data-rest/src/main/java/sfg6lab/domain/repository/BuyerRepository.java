//: sfg6lab.repository.BuyerManyToManyRepository.java

package sfg6lab.domain.repository;


import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sfg6lab.domain.model.Buyer;

import java.util.Optional;


@RepositoryRestResource(path = "buyers", excerptProjection = BuyerProjection.class)
public interface BuyerRepository extends
        ListCrudRepository<Buyer, Long>,
        ListPagingAndSortingRepository<Buyer, Long> {

    @Override
    Optional<Buyer> findById(Long id);

    @Override
    @RestResource(exported = false)
    void deleteById (Long id);
}
