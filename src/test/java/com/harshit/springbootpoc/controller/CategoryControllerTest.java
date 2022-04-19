package com.harshit.springbootpoc.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harshit.springbootpoc.dto.BookResponse;
import com.harshit.springbootpoc.dto.CategoryResponse;
import com.harshit.springbootpoc.model.Author;
import com.harshit.springbootpoc.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CategoryController.class)
        class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        return objectMapper.readValue(json, clazz);
    }


    @Test
    void getBooksByCategoryTest() throws Exception {
        Set<Author> authors = new HashSet<>();
        authors.add(new Author(1,"James","Harden","Science Friction"));
        authors.add(new Author(2,"Gon","Killua","Manga"));
        BookResponse book1 = new BookResponse(1,"Example","Example description","","Example Synopsis",300,20, LocalDate.parse("2019-03-01"),1500,true,authors);
        BookResponse book2 = new BookResponse(2,"Example 2","Example description 2","","Example Synopsis 2",500,15, LocalDate.parse("2018-03-01"),1400,true,authors);
        Set<BookResponse> bookResponses = new HashSet<>();
        bookResponses.add(book1);
        bookResponses.add(book2);
        CategoryResponse categoryResponse = new CategoryResponse(1,"Drama",bookResponses);

        Mockito.when(categoryService.getBooksByCategory("Drama")).thenReturn(categoryResponse);

        String uri = "/subjects/Drama";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        CategoryResponse categoryBooks = mapFromJson(content, CategoryResponse.class);
        assertEquals("Drama",categoryBooks.getCategoryName());
        assertEquals(2,categoryBooks.getBooks().size());
    }
}
