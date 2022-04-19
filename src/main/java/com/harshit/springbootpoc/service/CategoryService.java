package com.harshit.springbootpoc.service;

import com.harshit.springbootpoc.dto.CategoryResponse;
import com.harshit.springbootpoc.model.Category;

public interface CategoryService {
    CategoryResponse getBooksByCategory(String categoryName)throws Exception;

    CategoryResponse addCategory(Category category);
}
