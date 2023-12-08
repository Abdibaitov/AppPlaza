package peaksoft.appplaza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.appplaza.model.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("select user from User user where user.email=:email")
     Optional<User> findByEmail(@Param("email") String email);
}
