package com.sudip.store.electronicstore.repo;

import com.sudip.store.electronicstore.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, String> {

    //search product by title
    Page<Product> findProductByTitleContaining(String title, Pageable pageable);

    Page<Product> findProductByIsLiveTrue(Pageable pageable);

    List<Product> findProductByOriginalPriceGreaterThan(Double price);

    List<Product> findProductByOriginalPriceLessThan(Double price);
}
