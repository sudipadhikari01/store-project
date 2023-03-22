package com.sudip.store.electronicstore.services.Impl;

import com.sudip.store.electronicstore.dtos.CategoryDto;
import com.sudip.store.electronicstore.dtos.PageableResponse;
import com.sudip.store.electronicstore.entity.Category;
import com.sudip.store.electronicstore.exception.ResourceNotFoundException;
import com.sudip.store.electronicstore.repo.CategoryRepo;
import com.sudip.store.electronicstore.services.CategoryService;
import com.sudip.store.electronicstore.utils.PageableHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        String uuid = UUID.randomUUID().toString();
        Category category = modelMapper.map(categoryDto, Category.class);
        category.setCategoryId(uuid);
        Category category1 = categoryRepo.save(category);
        return modelMapper.map(category1, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(String id, CategoryDto categoryDto) {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found with id:" + id));
        category.setDescription(categoryDto.getDescription());
        category.setTitle(categoryDto.getTitle());
        category.setCoverImage(categoryDto.getCoverImage());
        Category save = categoryRepo.save(category);
        return modelMapper.map(save, CategoryDto.class);
    }

    @Override
    public void removeCategory(String id) {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found with id:" + id));

        categoryRepo.delete(category);
    }

    @Override
    public PageableResponse<CategoryDto> getAllCategories(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("Asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Category> categoryPage = categoryRepo.findAll(pageable);

        PageableResponse<CategoryDto> pageableResponse = PageableHelper.getPageableResponse(categoryPage, CategoryDto.class);

        return pageableResponse;
    }

    @Override
    public CategoryDto getCategoryById(String id) {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found with id:" + id));

        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategoryByName(String categoryName) {
        List<Category> categoryByTitle = categoryRepo.findCategoryByTitle(categoryName);
        return categoryByTitle.stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }
}
