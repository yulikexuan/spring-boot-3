//: sfg6lab.repository.MemberEmbededMailRepository.java

package sfg6lab.repository;


import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import sfg6lab.domain.model.Member;

import java.util.Optional;


public interface MemberEmbededMailRepository extends
        ListCrudRepository<Member, Long>,
        ListPagingAndSortingRepository<Member, Long>{

    Optional<Member> findByEmail(String email);

}
