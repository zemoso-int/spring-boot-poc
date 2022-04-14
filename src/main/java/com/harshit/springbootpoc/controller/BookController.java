package com.harshit.springbootpoc.controller;

import com.harshit.springbootpoc.dto.BookCategoryRequest;
import com.harshit.springbootpoc.dto.BookResponse;
import com.harshit.springbootpoc.dto.BooksHighlightsResponse;
import com.harshit.springbootpoc.exception.BookNotFoundException;
import com.harshit.springbootpoc.model.Book;
import com.harshit.springbootpoc.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Slf4j
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    Logger logger = LoggerFactory.getLogger(BookController.class);

    @GetMapping(value = "/books")
    public List<BookResponse> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping(value = "/books/highlights")
    public List<BooksHighlightsResponse> getBooksHighlights(){
        return bookService.getBookHighlights();
    }

    @GetMapping(value = "/books/search")
    public List<BookResponse> getBooksBasedOnKeyword(@RequestParam("keyword") String keyword){
        return bookService.getBooksBasedOnSearch(keyword);
    }

    @GetMapping(value = "/books/{bookId}")
    public BookResponse getBookDetailsById(@PathVariable("bookId") Integer id)throws BookNotFoundException {
            return bookService.getBookDetails(id);
    }

    @PostMapping(value = "/books")
    public ResponseEntity<BookResponse> addBook(@Valid @RequestBody BookCategoryRequest bookCategoryRequest){
        log.debug("Inserting a Book");
        return new ResponseEntity<>(bookService.addBookDetails(new Book(bookCategoryRequest)), HttpStatus.CREATED);
    }

    @PutMapping(value = "/books/{bookId}")
    public ResponseEntity<BookResponse> updateBook(@Valid @RequestBody BookCategoryRequest bookCategoryRequest){
        log.debug("Updating a Book");
        return new ResponseEntity<>(bookService.updateBookDetails(new Book(bookCategoryRequest)), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/books/{bookId}")
    public ResponseEntity<Boolean> deleteBook(@PathVariable("bookId") Integer bookId) throws BookNotFoundException {
        if( bookService.deleteBookDetails(bookId)){
            log.debug("Deleted a Book");
            return new ResponseEntity<>(true,HttpStatus.ACCEPTED);
        }
        throw new BookNotFoundException();
    }

}
