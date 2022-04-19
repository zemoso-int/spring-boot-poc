package com.harshit.springbootpoc.dto;

import com.harshit.springbootpoc.model.Book;
import com.harshit.springbootpoc.model.BookHighlights;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BooksHighlightsResponse {
    private Integer bookId;
    private String title;
    private Set<BookHighlights> bookHighlights;

    public BooksHighlightsResponse(Book book) {
        this.bookId = book.getBookId();
        this.title = book.getTitle();
        this.bookHighlights = book.getBookHighlights();
    }
}
