package com.codenetworkz.productservice.services;

import com.codenetworkz.productservice.models.Category;
import com.codenetworkz.productservice.models.Product;

import java.util.List;

public interface CategoryService {

    List<Product> getProductCategoryByName(String name);


    List<Category> getAllCategories();
}
