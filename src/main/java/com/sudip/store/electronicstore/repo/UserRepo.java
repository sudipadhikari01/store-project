package com.sudip.store.electronicstore.repo;

import com.sudip.store.electronicstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, String> {
    Optional<User> findUserByEmail(String email);

    List<User> findUserByNameContaining(String name);
}
