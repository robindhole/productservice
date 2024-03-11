package com.codenetworkz.productservice.dtos;

import com.codenetworkz.productservice.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListProductResponse {

    private List<Product> data;
    private String status;
    private String massage;
    private String errors;
}
