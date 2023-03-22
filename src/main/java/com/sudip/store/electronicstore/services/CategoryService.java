package com.sudip.store.electronicstore.services;

import com.sudip.store.electronicstore.dtos.CategoryDto;
import com.sudip.store.electronicstore.dtos.PageableResponse;
import com.sudip.store.electronicstore.entity.Category;

import java.util.List;

public interface CategoryService {

    //    create
    CategoryDto createCategory(CategoryDto categoryDto);

    //    update
    CategoryDto updateCategory(String id, CategoryDto categoryDto);

    //    delete
    void removeCategory(String id);

    //    get all categories
    PageableResponse<CategoryDto> getAllCategories(int pageNumber, int pageSize,String sortBy,String sortDir);

    //    get single category detail
    CategoryDto getCategoryById(String id);

    //    search
    List<CategoryDto> getCategoryByName(String categoryName);
}
