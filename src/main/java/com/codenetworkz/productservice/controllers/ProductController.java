package com.codenetworkz.productservice.controllers;

import com.codenetworkz.productservice.models.Category;
import com.codenetworkz.productservice.models.Product;
import com.codenetworkz.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

   final private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product){
       return productService.create(product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory(),
                product.getImageURL());
    }

    @GetMapping("/{id}")
    public Product getOneProduct(@PathVariable("id") Long productId){
        return productService.getOneProduct(productId);
    }

    @GetMapping("/category/{name}")
    public List<Product>  getProductByCategoryName(@PathVariable String name){
        return productService.getProductCategoryByName(name);
    }
    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProduct();
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable("id") Long productId){
        return productService.deleteProduct(productId);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable("id") Long productId){
        return productService.updateProduct(product, productId);

    }

    @GetMapping("/categories")
    public List<Category> getAllCategories(){
        return productService.getAllCategories();
    }

}
