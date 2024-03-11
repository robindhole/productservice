package com.codenetworkz.productservice.controllers;

import com.codenetworkz.productservice.dtos.ListProductResponse;
import com.codenetworkz.productservice.dtos.ProductResponse;
import com.codenetworkz.productservice.models.Product;
import com.codenetworkz.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createProduct(@RequestBody Product product){

        Product responseData = productService.create(product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory(),
                product.getImageURL());

        ProductResponse response =new ProductResponse();
        if(responseData!=null && responseData.getTitle().equals(product.getTitle())) {
            response.setData(responseData);
            response.setStatus(HttpStatus.ACCEPTED.toString());
            response.setMassage("Product has been ADD successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }else {
            response.setData(null);
            response.setStatus(HttpStatus.BAD_REQUEST.toString());
            response.setMassage("Product has been NOT ADD successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneProduct(@PathVariable("id") Long productId){
        Product responseData = productService.getOneProduct(productId);
        ProductResponse response =new ProductResponse();
        if(responseData!=null && responseData.getId() !=null) {
            response.setData(responseData);
            response.setStatus(HttpStatus.ACCEPTED.toString());
            response.setMassage(" Found Product with id " + productId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            response.setData(responseData);
            response.setStatus(HttpStatus.BAD_REQUEST.toString());
            response.setMassage("Not found Product with id " + productId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }



    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        List<Product> responseData = productService.getAllProduct();
        ListProductResponse response =new ListProductResponse();
        if(responseData!=null && !responseData.isEmpty() && responseData.size()>1 ){
            response.setData(responseData);
            response.setStatus(HttpStatus.ACCEPTED.toString());
            response.setMassage("List of Product founds");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            response.setData(null);
            response.setStatus(HttpStatus.NO_CONTENT.toString());
            response.setMassage("Products Not available,please Add new Product");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long productId){
        ProductResponse response = new ProductResponse();
            Product responseData = productService.deleteProduct(productId);
        if(responseData!=null && responseData.getId().equals(responseData)) {
            response.setData(responseData);
            response.setStatus(HttpStatus.ACCEPTED.toString());
            response.setMassage("Product has been deleted with product id is " + responseData.getId());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            response.setData(null);
            response.setStatus(HttpStatus.BAD_REQUEST.toString());
            response.setMassage("Product not found  with ProductId is " + responseData.getId());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable("id") Long productId){
        Product responseData = productService.updateProduct(product, productId);
        ProductResponse response =new ProductResponse();
        if(responseData!=null && responseData.getId().equals(productId)) {
            response.setData(responseData);
            response.setStatus(HttpStatus.ACCEPTED.toString());
            response.setMassage("Product has been updated with product id is "+productId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            response.setData(null);
            response.setStatus(HttpStatus.BAD_REQUEST.toString());
            response.setMassage("Product has been not updated with product id is "+productId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchUpdateProduct(@RequestBody Product product, @PathVariable("id") Long productId){
        Product responseData = productService.updateProduct(product, productId, "patch");
        ProductResponse response =new ProductResponse();
        if(responseData!=null && responseData.getId().equals(productId)) {
            response.setData(responseData);
            response.setStatus(HttpStatus.ACCEPTED.toString());
            response.setMassage("Product has been updated with product id is "+productId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            response.setData(null);
            response.setStatus(HttpStatus.BAD_REQUEST.toString());
            response.setMassage("Product has been not updated with product id is "+productId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
