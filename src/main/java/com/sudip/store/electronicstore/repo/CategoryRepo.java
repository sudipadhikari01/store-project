package com.sudip.store.electronicstore.repo;

import com.sudip.store.electronicstore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, String> {

    List<Category> findCategoryByTitle(String title);
}
