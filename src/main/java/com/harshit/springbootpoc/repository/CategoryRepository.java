package com.harshit.springbootpoc.repository;


import com.harshit.springbootpoc.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Category findByCategoryName(String name);
}
