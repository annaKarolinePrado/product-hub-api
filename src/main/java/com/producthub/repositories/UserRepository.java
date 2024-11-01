package com.producthub.repositories;

import com.producthub.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmailContainingIgnoreCase(String email);

    Optional<User> findByEmail(String email);

    boolean existsByNickname(String nickname);
}
