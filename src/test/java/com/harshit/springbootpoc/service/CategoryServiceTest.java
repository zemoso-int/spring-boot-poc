package com.harshit.springbootpoc.service;

import com.harshit.springbootpoc.dto.CategoryResponse;
import com.harshit.springbootpoc.exception.CategoryNotFoundException;
import com.harshit.springbootpoc.model.Author;
import com.harshit.springbootpoc.model.Book;
import com.harshit.springbootpoc.model.Category;
import com.harshit.springbootpoc.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {


    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoriesService categoryService;


    @Test
    void getBooksByCategoryTest() throws Exception {
        Set<Author> authors = new HashSet<>();
        authors.add(new Author(1,"James","Harden","Science Friction"));
        authors.add(new Author(2,"Gon","Killua","Manga"));
        Book book1 = new Book(1,"Example","Example description","","Example Synopsis",300,20, LocalDate.parse("2019-03-01"),1500,true,null,null,null,authors);
        Book book2 = new Book(2,"Example 2","Example description 2","","Example Synopsis 2",500,15, LocalDate.parse("2018-03-01"),1400,true,null,null,null,authors);
        Set<Book> books = new HashSet<>();
        books.add(book1);
        books.add(book2);
        Category category = new Category(1,"Drama",books);
        Mockito.when(categoryRepository.findByCategoryName(Mockito.anyString())).thenReturn(category);
        CategoryResponse categoryResponse = categoryService.getBooksByCategory("Drama");
        assertEquals(2,categoryResponse.getBooks().size());
        assertEquals(1,categoryResponse.getCategoryId());
    }

    @Test
    void testAddCategory(){
        Category category = new Category(1,"Drama",null);
        Mockito.when(categoryRepository.save(category)).thenReturn(category);
        CategoryResponse categoryResponse = categoryService.addCategory(category);
        assertEquals("Drama",categoryResponse.getCategoryName());
    }
}
