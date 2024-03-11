package com.codenetworkz.productservice.service.impl;

import com.codenetworkz.productservice.dtos.FakeStoreProductRequestResponseDto;
import com.codenetworkz.productservice.handler.FakeStoreApiException;
import com.codenetworkz.productservice.handler.ProductNotFoundException;
import com.codenetworkz.productservice.mapper.CategoryMapper;
import com.codenetworkz.productservice.mapper.ProductMapper;
import com.codenetworkz.productservice.models.Category;
import com.codenetworkz.productservice.models.Product;
import com.codenetworkz.productservice.services.ProductService;
import com.codenetworkz.productservice.utility.StringUtility;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FakeStoreServiceImpl implements ProductService {

   final private RestTemplate restTemplate;
    final private ProductMapper productMapper;
    final private CategoryMapper categoryMapper;

    final private String BaseURL="https://fakestoreapi.com/products/";

    public FakeStoreServiceImpl(RestTemplate restTemplate, ProductMapper productMapper, CategoryMapper categoryMapper) {
        this.restTemplate = restTemplate;
        this.productMapper = productMapper;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Product create(String title, String description, Double price, Category category, String imageUrl) {
        FakeStoreProductRequestResponseDto responseDto;
        FakeStoreProductRequestResponseDto productFakeStoreDto= new FakeStoreProductRequestResponseDto();
        productFakeStoreDto.setTitle(title);
        productFakeStoreDto.setDescription(description);
        productFakeStoreDto.setPrice(price);
        productFakeStoreDto.setCategory(category.getName());
        productFakeStoreDto.setImage(imageUrl);
        try {
             responseDto = restTemplate.postForObject(BaseURL, productFakeStoreDto, FakeStoreProductRequestResponseDto.class);
        }catch (Exception e){
            throw new FakeStoreApiException(e.getMessage());
        }
        return productMapper.toProduct(responseDto );
    }

    @Override
    public List<Product> getAllProduct() {
        FakeStoreProductRequestResponseDto[] responseDtos;
        try {
             responseDtos = restTemplate.
                    getForObject(BaseURL, FakeStoreProductRequestResponseDto[].class);
        }catch (Exception e){
            throw new FakeStoreApiException(e.getMessage());
        }
        return productMapper.toProducts(responseDtos);
    }

    @Override
    public Product getOneProduct(Long productId) {
        FakeStoreProductRequestResponseDto responseDto;
        try{
             responseDto=  restTemplate.
                    getForObject(BaseURL + productId, FakeStoreProductRequestResponseDto.class);
        }catch (Exception e){
            throw new FakeStoreApiException(e.getMessage());
        }

        return productMapper.toProduct(responseDto);
    }

    @Override
    public Product deleteProduct(Long productId) {
        Product oneProduct = getOneProduct(productId);
        Product deleted = new Product();

        FakeStoreProductRequestResponseDto response = new FakeStoreProductRequestResponseDto();
        if (oneProduct != null) {
            String url = BaseURL +"{id}";
            Map<String, String> param = new HashMap<>();
            param.put("id", String.valueOf(productId));
            response = restTemplate.exchange(url, HttpMethod.PUT, null, FakeStoreProductRequestResponseDto.class, param).getBody();
        }
        return productMapper.toProduct(response);
    }

    @Override
    public Product updateProduct(Product product,Long productId, String type)  {
        HttpEntity<FakeStoreProductRequestResponseDto> response;
        FakeStoreProductRequestResponseDto request;
        Product  oneProduct = getOneProduct(productId);


        Product updatedProduct=new Product();

        if(oneProduct!=null){
            request = checkMethodTypeAndSetProductDetails(product, type, oneProduct);
          String url=  BaseURL +"{id}";
            HttpHeaders headers = new HttpHeaders();
            Map<String, String> param = new HashMap<>();
            param.put("id", String.valueOf(productId));
            HttpEntity<FakeStoreProductRequestResponseDto> requestEntity = new HttpEntity<FakeStoreProductRequestResponseDto>(request, headers);
            try{
               response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, FakeStoreProductRequestResponseDto.class, param);
            }catch (Exception e){
                throw new FakeStoreApiException(e.getMessage());
            }

             updatedProduct = productMapper.toProduct(response.getBody());
        }
        return updatedProduct;
    }
    @Override
    public Product updateProduct(Product product, Long productId) {
        return  updateProduct(product, productId, null);
    }
    private FakeStoreProductRequestResponseDto checkMethodTypeAndSetProductDetails(Product product, String type, Product oneProduct) {
        FakeStoreProductRequestResponseDto request;
        if(type!=null && type.equalsIgnoreCase("patch")){
            if(StringUtility.isNotEmptyString(product.getTitle())){
                oneProduct.setTitle(product.getTitle());
            }
            if(product.getCategory()!= null){
                oneProduct.setCategory(product.getCategory());

            }
            if(StringUtility.isNotEmptyString(product.getDescription())){
                oneProduct.setDescription(product.getDescription());
            }
            if(product.getPrice()!=null){
                oneProduct.setPrice(product.getPrice());
            }
            if(StringUtility.isNotEmptyString(product.getImageURL())){
                oneProduct.setImageURL(product.getImageURL());
            }
             request = productMapper.toFakeStoreResponseDto(oneProduct);
        }else {
             request = productMapper.toFakeStoreResponseDto(product);
        }
        return request;
    }

}
