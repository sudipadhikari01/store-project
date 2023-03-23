package com.sudip.store.electronicstore.services.Impl;

import com.sudip.store.electronicstore.dtos.PageableResponse;
import com.sudip.store.electronicstore.dtos.ProductDto;
import com.sudip.store.electronicstore.entity.Product;
import com.sudip.store.electronicstore.exception.ResourceNotFoundException;
import com.sudip.store.electronicstore.repo.ProductRepo;
import com.sudip.store.electronicstore.services.ProductService;
import com.sudip.store.electronicstore.utils.PageableHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepo productRepo;
    private ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo, ModelMapper modelMapper) {
        this.productRepo = productRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        String uuid = UUID.randomUUID().toString();
        Product product = modelMapper.map(productDto, Product.class);
        product.setId(uuid);
        product.setAddedDate(new Date());
        Product save = productRepo.save(product);
        return modelMapper.map(save, ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(String id, ProductDto productDto) {
        Product product = productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        Product product1 = Product.builder().id(id).title(productDto.getTitle()).quantity(productDto.getQuantity())
                .originalPrice(product.getOriginalPrice()).discountedPrice(productDto.getDiscountedPrice()).addedDate(productDto.getAddedDate()).isLive(productDto.isLive()).isStockAvailable(productDto.isStockAvailable()).build();
        Product save = productRepo.save(product1);
        return modelMapper.map(save, ProductDto.class);
    }

    @Override
    public void removeProduct(String id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        productRepo.delete(product);
    }

    @Override
    public PageableResponse<ProductDto> getAllProduct(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> productPage = productRepo.findAll(pageable);
        PageableResponse<ProductDto> pageableResponse = PageableHelper.getPageableResponse(productPage, ProductDto.class);
        return pageableResponse;
    }

    @Override
    public ProductDto findProductById(String id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> searchProductByTitle(String title, int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> productPage = productRepo.findProductByTitleContaining(title, pageable);
        PageableResponse<ProductDto> pageableResponse = PageableHelper.getPageableResponse(productPage, ProductDto.class);

        return pageableResponse;
    }

    @Override
    public List<ProductDto> productGreaterThanGivenPrice(Double price) {
        return null;
    }


    @Override
    public PageableResponse<ProductDto> findAllLiveProduct(int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> productByLiveTrue = productRepo.findProductByIsLiveTrue(pageable);
        PageableResponse<ProductDto> pageableResponse = PageableHelper.getPageableResponse(productByLiveTrue, ProductDto.class);
        return pageableResponse;
    }

    @Override
    public List<ProductDto> productLessThanGivenPrice(Double price) {
        return null;
    }
}
