package com.harshit.springbootpoc.service;

import com.harshit.springbootpoc.dto.BookResponse;
import com.harshit.springbootpoc.dto.BooksHighlightsResponse;
import com.harshit.springbootpoc.exception.BookNotFoundException;
import com.harshit.springbootpoc.model.Book;

import java.util.List;

@SuppressWarnings("ALL")
public interface BookService {

    List<BooksHighlightsResponse> getBookHighlights();

    List<BookResponse> getBooksBasedOnSearch(String searchKeyword);

    List<BookResponse> getAllBooks();

    BookResponse getBookDetails(Integer bookId) throws BookNotFoundException;

    Book getBookById(Integer bookId) throws BookNotFoundException;

    BookResponse addBookDetails(Book book);

    BookResponse updateBookDetails(Book book);

    boolean deleteBookDetails(Integer bookId)throws BookNotFoundException;
}
