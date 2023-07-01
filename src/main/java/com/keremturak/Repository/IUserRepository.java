package com.keremturak.Repository;

import com.keremturak.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    List<User> findAllByOrderByName();
    List<User> findAllByNameContaining(String name);
    List<User> findAllByEmailContainingIgnoreCase(String email);
    List<User> findAllByEmailEndingWith(String email);

    Boolean existsByEmailAndPassword(String email, String password);
    Optional<User> findByEmailAndPassword(String email, String password);


}
