package com.codenetworkz.productservice.controllers;

import com.codenetworkz.productservice.dtos.ListCategories;
import com.codenetworkz.productservice.dtos.ListProductResponse;
import com.codenetworkz.productservice.models.Category;
import com.codenetworkz.productservice.models.Product;
import com.codenetworkz.productservice.services.CategoryService;
import com.codenetworkz.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    final private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/products/category/{name}")
    public ResponseEntity<?> getProductByCategoryName(@PathVariable String name) {
        List<Product> responseData = categoryService.getProductCategoryByName(name);
        ListProductResponse response = new ListProductResponse();
        if (!responseData.equals(null) && !responseData.isEmpty()) {
            response.setData(responseData);
            response.setStatus(HttpStatus.ACCEPTED.toString());
            response.setMassage("Products available with provided category name " + name);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setData(null);
            response.setStatus(HttpStatus.BAD_REQUEST.toString());
            response.setMassage("Products not available with provided category name " + name);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/products/categories")
    public ResponseEntity<?> getAllCategories() {
        List<Category> responseData = categoryService.getAllCategories();

        ListCategories response = new ListCategories();
        if (!responseData.equals(null)) {
            response.setData(responseData);
            response.setStatus(HttpStatus.ACCEPTED.toString());
            response.setMassage("List of Categories founds");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setData(null);
            response.setStatus(HttpStatus.BAD_REQUEST.toString());
            response.setMassage("Categories Not available,please Add new Category");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}