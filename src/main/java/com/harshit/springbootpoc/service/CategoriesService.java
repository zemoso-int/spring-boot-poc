package com.harshit.springbootpoc.service;

import com.harshit.springbootpoc.dto.CategoryResponse;
import com.harshit.springbootpoc.model.Category;
import com.harshit.springbootpoc.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriesService implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryResponse getBooksByCategory(String categoryName) throws Exception {
        Category category = categoryRepository.findByCategoryName(categoryName);
        if (category == null) {
            throw new Exception();
        }
        return new CategoryResponse(category);
    }

    @Override
    public CategoryResponse addCategory(Category category) {
        return new CategoryResponse(categoryRepository.save(category));
    }
}
