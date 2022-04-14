package com.harshit.springbootpoc.controller;

import com.harshit.springbootpoc.dto.CategoryRequest;
import com.harshit.springbootpoc.dto.CategoryResponse;
import com.harshit.springbootpoc.exception.CategoryNotFoundException;
import com.harshit.springbootpoc.model.Category;
import com.harshit.springbootpoc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/subjects")
    public CategoryResponse addCategory(@RequestBody CategoryRequest categoryRequest){
        return categoryService.addCategory(new Category(categoryRequest));
    }


    @GetMapping(value = "/subjects/{category}")
    public CategoryResponse getBooksByCategory(@PathVariable("category") String category) throws Exception {
        return categoryService.getBooksByCategory(category);
    }
}
