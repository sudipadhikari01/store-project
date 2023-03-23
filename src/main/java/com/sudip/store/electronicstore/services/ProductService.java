package com.sudip.store.electronicstore.services;

import com.sudip.store.electronicstore.dtos.PageableResponse;
import com.sudip.store.electronicstore.dtos.ProductDto;

import java.util.List;

public interface ProductService {

    //    create
    ProductDto createProduct(ProductDto productDto);

    //    update
    ProductDto updateProduct(String id, ProductDto productDto);

    //    delete
    void removeProduct(String id);

    //    get all product
    PageableResponse<ProductDto> getAllProduct(int pageNumber, int pageSize, String sortBy, String sortDir);

    //    find product by id (single product)
    ProductDto findProductById(String id);

    //    search product by title
    PageableResponse<ProductDto> searchProductByTitle(String title,int pageNumber, int pageSize, String sortBy, String sortDir);

    //    find product greater than give price
    List<ProductDto> productGreaterThanGivenPrice(Double price);


//
PageableResponse<ProductDto> findAllLiveProduct(int pageNumber, int pageSize, String sortBy, String sortDir);
    //    find all product less than given price
    List<ProductDto> productLessThanGivenPrice(Double price);

}
