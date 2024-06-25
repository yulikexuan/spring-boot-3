package sfg6lab.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sfg6lab.domain.model.User;

import java.util.List;


@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();

}
