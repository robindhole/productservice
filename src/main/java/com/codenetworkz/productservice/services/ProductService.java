package com.codenetworkz.productservice.services;

import com.codenetworkz.productservice.models.Category;
import com.codenetworkz.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product create(String title,
                   String description,
                   Double price,
                   Category category,
                   String imageUrl);

    List<Product> getAllProduct();

    Product getOneProduct(Long productId);



    Product deleteProduct(Long productId);

    Product updateProduct(Product product, Long productId, String type);

    Product updateProduct(Product product, Long productId);

}
