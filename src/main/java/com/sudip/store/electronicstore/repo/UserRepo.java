package com.sudip.store.electronicstore.repo;

import com.sudip.store.electronicstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
}
