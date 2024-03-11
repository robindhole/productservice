package com.codenetworkz.productservice.service.impl;

import com.codenetworkz.productservice.dtos.FakeStoreProductRequestResponseDto;
import com.codenetworkz.productservice.handler.FakeStoreApiException;
import com.codenetworkz.productservice.mapper.CategoryMapper;
import com.codenetworkz.productservice.mapper.ProductMapper;
import com.codenetworkz.productservice.models.Category;
import com.codenetworkz.productservice.models.Product;
import com.codenetworkz.productservice.services.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreCategoryServiceImpl implements CategoryService {

    final private RestTemplate restTemplate;
    final private ProductMapper productMapper;
    final private CategoryMapper categoryMapper;

    final private String BaseURL="https://fakestoreapi.com/products/";

    public FakeStoreCategoryServiceImpl(RestTemplate restTemplate, ProductMapper productMapper, CategoryMapper categoryMapper) {
        this.restTemplate = restTemplate;
        this.productMapper = productMapper;
        this.categoryMapper = categoryMapper;
    }


    @Override
    public List<Product> getProductCategoryByName(String name) {
        FakeStoreProductRequestResponseDto[] response;
        try{
             response = restTemplate.
                    getForEntity(BaseURL +"/category/" + name, FakeStoreProductRequestResponseDto[].class).getBody();

        }catch (Exception e){
            throw new FakeStoreApiException(e.getMessage());
        }

        return productMapper.toProducts(response);

    }
    @Override
    public List<Category> getAllCategories() {
        String[] response;
        try {
            response = restTemplate.
                    getForObject(BaseURL + "/categories", String[].class);
        }catch (Exception e){
            throw new FakeStoreApiException(e.getMessage());
        }
        return categoryMapper.toCategoryList(response);
    }

}
