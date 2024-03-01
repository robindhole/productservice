package com.codenetworkz.productservice.mapper;

import com.codenetworkz.productservice.dtos.FakeStoreProductRequestResponseDto;
import com.codenetworkz.productservice.models.Category;
import com.codenetworkz.productservice.models.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ProductMapper {


    public List<Product> toProducts(FakeStoreProductRequestResponseDto[] fakeStoreProductRequestResponseDto) {
        List<Product> products = new ArrayList<Product>();
        if(fakeStoreProductRequestResponseDto!=null) {
            for (FakeStoreProductRequestResponseDto fakeStoreResponse : fakeStoreProductRequestResponseDto) {
                Product product = toProduct(fakeStoreResponse);
                products.add(product);
            }
        }
        return products;
    }

    public Product toProduct(FakeStoreProductRequestResponseDto fakeStoreProductRequestResponseDto) {
        Product product = new Product();
        if(fakeStoreProductRequestResponseDto!=null) {
            product.setId(fakeStoreProductRequestResponseDto.getId());
            product.setTitle(fakeStoreProductRequestResponseDto.getTitle());
            product.setPrice(fakeStoreProductRequestResponseDto.getPrice());
            product.setDescription(fakeStoreProductRequestResponseDto.getDescription());
            Category productCategory = new Category();
            productCategory.setName(fakeStoreProductRequestResponseDto.getCategory());
            product.setCategory(productCategory);
            product.setImageURL(fakeStoreProductRequestResponseDto.getImage());
        }
        return product;
    }

    public FakeStoreProductRequestResponseDto toFakeStoreResponseDto(Product product) {
        FakeStoreProductRequestResponseDto fakeStoreProductRequestResponseDto = new FakeStoreProductRequestResponseDto();
       if (product!=null) {
           fakeStoreProductRequestResponseDto.setId(product.getId());
           fakeStoreProductRequestResponseDto.setTitle(product.getTitle());
           fakeStoreProductRequestResponseDto.setPrice(product.getPrice());
           fakeStoreProductRequestResponseDto.setDescription(product.getDescription());
           fakeStoreProductRequestResponseDto.setCategory(product.getCategory().getName());
           fakeStoreProductRequestResponseDto.setImage(product.getImageURL());
       }
        return fakeStoreProductRequestResponseDto;
    }
}
