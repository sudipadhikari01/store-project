package com.sudip.store.electronicstore.controller;

import com.sudip.store.electronicstore.dtos.CategoryDto;
import com.sudip.store.electronicstore.dtos.PageableResponse;
import com.sudip.store.electronicstore.payload.ResponseMessage;
import com.sudip.store.electronicstore.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //    create
    @PostMapping()
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto category = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    //    update
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable String id, @RequestBody CategoryDto categoryDto) {
        CategoryDto categoryDto1 = categoryService.updateCategory(id, categoryDto);
        return new ResponseEntity<>(categoryDto1, HttpStatus.OK);
    }

    //    delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteCategory(@PathVariable String id) {
        categoryService.removeCategory(id);
        ResponseMessage success = ResponseMessage.builder().message("User deleted with id: " + id + " successfully")
                .httpStatus(HttpStatus.OK).success(true).build();
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    //    get all categories
    @GetMapping
    public ResponseEntity<PageableResponse<CategoryDto>> getAllCategories(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber, @RequestParam(value = "pageSize", defaultValue = "3", required = false) int pageSize, @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy, @RequestParam(value = "sortDir", defaultValue = "Asc", required = false) String sortDir) {
        PageableResponse<CategoryDto> allCategories = categoryService.getAllCategories(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }

    //    find by id
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findCategoryByID(@PathVariable String id) {
        CategoryDto categoryById = categoryService.getCategoryById(id);
        return new ResponseEntity<>(categoryById, HttpStatus.OK);
    }

    //    search by name
    @GetMapping("/search/{title}")
    public ResponseEntity<List<CategoryDto>> findCategoryByTitle(@PathVariable String title) {
        List<CategoryDto> categoryByName = categoryService.getCategoryByName(title);
        return new ResponseEntity<>(categoryByName, HttpStatus.OK);
    }

}
