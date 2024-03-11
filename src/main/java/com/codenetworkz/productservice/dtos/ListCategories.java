package com.codenetworkz.productservice.dtos;

import com.codenetworkz.productservice.models.Category;
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
public class ListCategories {

    private List<Category> data;
    private String status;
    private String massage;
    private String errors;
}
