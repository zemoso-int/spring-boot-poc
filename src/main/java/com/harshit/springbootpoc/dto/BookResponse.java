package com.harshit.springbootpoc.dto;

import com.harshit.springbootpoc.model.Author;
import com.harshit.springbootpoc.model.Book;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookResponse {

    private Integer bookId;

    private String title;

    private String description;

    private String photoPath;

    private String synopsis;

    private Integer totalPages;

    private Integer approximateMinutes;

    private LocalDate publishedDate;

    private Integer numberOfReads;

    private Boolean audioAvailability;

    private Set<Author> bookAuthor;

    public BookResponse(Book book) {
        this.bookId = book.getBookId();
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.photoPath = book.getPhotoPath();
        this.synopsis = book.getSynopsis();
        this.totalPages = book.getTotalPages();
        this.approximateMinutes = book.getApproximateMinutes();
        this.publishedDate = book.getPublishedDate();
        this.numberOfReads = book.getNumberOfReads();
        this.audioAvailability = book.getAudioAvailability();
        this.setBookAuthor(book.getBookAuthor());
    }
}
