package com.example.module12.repository;

import com.example.module12.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByNameOrderByAgeAsc(String name);
    Optional<User> findUserByUsername(String username);
}
