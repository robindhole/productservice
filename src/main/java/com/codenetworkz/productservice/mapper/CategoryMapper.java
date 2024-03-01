package com.codenetworkz.productservice.mapper;

import com.codenetworkz.productservice.dtos.FakeStoreCategoriesDto;
import com.codenetworkz.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {

   public List<Category> toCategoryList(String[] categories){
        List<Category> categoryList=new ArrayList<>();
        if(categories!=null) {
            for (String category : categories) {
                Category category1 = new Category();
                category1.setName(category);
                categoryList.add(category1);
            }
        }
        return categoryList;
    }

}
