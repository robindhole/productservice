package com.codenetworkz.productservice.dtos;

import com.codenetworkz.productservice.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Product data;
    private String status;
    private String massage;
    private String errors;

}
