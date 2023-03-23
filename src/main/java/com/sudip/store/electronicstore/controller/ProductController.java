package com.sudip.store.electronicstore.controller;

import com.sudip.store.electronicstore.dtos.PageableResponse;
import com.sudip.store.electronicstore.dtos.ProductDto;
import com.sudip.store.electronicstore.payload.ResponseMessage;
import com.sudip.store.electronicstore.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;
    private ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;

    }


    //    create product
    @PostMapping
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto) {
        ProductDto product = productService.createProduct(productDto);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    //    update product
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable String id) {
        ProductDto productDto1 = productService.updateProduct(id, productDto);
        return new ResponseEntity<>(productDto1, HttpStatus.OK);
    }


    //    delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteProduct(@PathVariable String id) {
        productService.removeProduct(id);
        ResponseMessage build = ResponseMessage.builder().message("Product deleted with id: " + id)
                .success(true).httpStatus(HttpStatus.OK).build();
        return new ResponseEntity<>(build, HttpStatus.OK);

    }

    //    get all products
    @GetMapping
    public ResponseEntity<PageableResponse<ProductDto>> getAllProduct(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber, @RequestParam(value = "pageSize", defaultValue = "3", required = false) int pageSize, @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy, @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        PageableResponse<ProductDto> allProduct = productService.getAllProduct(pageNumber, pageSize, sortBy, sortDir);

        return new ResponseEntity(allProduct, HttpStatus.OK);

    }

    //    get single product
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable String id) {
        ProductDto productById = productService.findProductById(id);
        return new ResponseEntity<>(productById, HttpStatus.OK);

    }

    //    get product that  are live
    @GetMapping("/search/live")
    public ResponseEntity<PageableResponse<ProductDto>> getAllProductLive(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber, @RequestParam(value = "pageSize", defaultValue = "3", required = false) int pageSize, @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy, @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        PageableResponse<ProductDto> allProduct = productService.findAllLiveProduct(pageNumber, pageSize, sortBy, sortDir);

        return new ResponseEntity(allProduct, HttpStatus.OK);

    }


    //    search product by title
    @GetMapping("/search/{title}")
    public ResponseEntity<PageableResponse<ProductDto>> findProductByTitle(@PathVariable String title, @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber, @RequestParam(value = "pageSize", defaultValue = "3", required = false) int pageSize, @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy, @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        PageableResponse<ProductDto> productDtoPageableResponse = productService.searchProductByTitle(title, pageNumber, pageSize, sortBy, sortDir);

        return new ResponseEntity<>(productDtoPageableResponse, HttpStatus.OK);
    }


//


}
