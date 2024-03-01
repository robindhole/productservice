package com.codenetworkz.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductRequestResponseDto {

    private Long id;
    private String title;
    private String category;
    private Double price;
    private String description;
    private String image;

}
