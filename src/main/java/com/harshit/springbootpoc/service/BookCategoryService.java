package com.harshit.springbootpoc.service;

import com.harshit.springbootpoc.dto.BookResponse;
import com.harshit.springbootpoc.dto.BooksHighlightsResponse;
import com.harshit.springbootpoc.exception.BookNotFoundException;
import com.harshit.springbootpoc.model.Book;
import com.harshit.springbootpoc.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookCategoryService implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<BooksHighlightsResponse> getBookHighlights() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(BooksHighlightsResponse::new).collect(Collectors.toList());
    }

    @Override
    public List<BookResponse> getBooksBasedOnSearch(String keyword) {
        List<Book> books = bookRepository.findByTitleContains(keyword);
        if (books != null) {
            log.debug("Found Multiple Books with the given keyword");
            books.addAll(bookRepository.findByBookAuthorFirstNameOrBookAuthorLastNameContains(keyword, keyword));
            return books.stream().map(BookResponse::new).collect(Collectors.toList());
        }
        log.warn(" No book is found with given Keyword");
        return new ArrayList<>();
    }

    @Override
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll().stream().map(BookResponse::new).collect(Collectors.toList());
    }

    @Override
    public BookResponse getBookDetails(Integer bookId) throws BookNotFoundException {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isEmpty()) {
            throw new BookNotFoundException();
        }
        return book.map(BookResponse::new).orElse(null);
    }

    @Override
    public Book getBookById(Integer bookId) throws BookNotFoundException {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            return book.get();
        }
        throw new BookNotFoundException();
    }

    @Override
    public BookResponse addBookDetails(Book book) {
        return new BookResponse(bookRepository.save(book));
    }

    @Override
    public BookResponse updateBookDetails(Book book) {
        log.debug("Updating a Book Details");
        return new BookResponse(bookRepository.save(book));
    }

    @Override
    public boolean deleteBookDetails(Integer bookId) throws BookNotFoundException {
        getBookById(bookId);
        bookRepository.deleteById(bookId);
        return true;
    }


}
