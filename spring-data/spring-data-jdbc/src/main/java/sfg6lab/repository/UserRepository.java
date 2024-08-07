package sfg6lab.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;
import sfg6lab.domain.model.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();

    Optional<User> findByUsername(String username);

    List<User> findAllByOrderByUsernameAsc();

    List<User> findByCreatedBetween(LocalDateTime start, LocalDateTime end);

    List<User> findByUsernameAndEmail(String username, String email);

    List<User> findByUsernameOrEmail(String username, String email);

    List<User> findByUsernameIgnoreCase(String username);

    List<User> findByLevelOrderByUsernameDesc(int level);

    List<User> findByLevelGreaterThanEqual(int level);

    List<User> findByUsernameContaining(String text);

    List<User> findByUsernameLike(String text);

    List<User> findByUsernameStartingWith(String start);

    List<User> findByUsernameEndingWith(String end);

    List<User> findByActive(boolean active);

    List<User> findByCreatedIn(Collection<LocalDateTime> dates);

    List<User> findByCreatedNotIn(Collection<LocalDateTime> dates);

    Optional<User> findFirstByOrderByUsernameAsc();

    Optional<User> findTopByOrderByCreatedDesc();

    Page<User> findAll(Pageable pageable);

    List<User> findFirst2ByLevel(int level, Sort sort);

    List<User> findByLevel(int level, Sort sort);

    List<User> findByActive(boolean active, Pageable pageable);

    Streamable<User> findByEmailContaining(String text);

    Streamable<User> findByLevel(int level);

    @Query("SELECT COUNT(*) FROM users WHERE level = :level")
    int findNumberOfUsersByLevel(@Param("level") int level);

    @Query("SELECT COUNT(*) FROM users WHERE active = :active")
    int findNumberOfUsersByActivity(@Param("active") boolean active);

    @Query("SELECT * FROM USERS WHERE LEVEL = :LEVEL AND ACTIVE = :ACTIVE")
    Streamable<User> findByLevelAndActive(
            @Param("LEVEL") int level,
            @Param("ACTIVE") boolean active);

    @Query("SELECT level FROM users WHERE username = :username")
    int findLevelByUsername(@Param("username") String username);

    @Modifying
    @Query("UPDATE USERS SET level = :newLevel WHERE username = :username")
        int updateLevelByUsername(
            @Param("newLevel") int newLevel,
            @Param("username") String username);

    @Modifying
    @Query("DELETE FROM USERS WHERE username = :username")
    int deleteByUsername(@Param("username") String username);

}
