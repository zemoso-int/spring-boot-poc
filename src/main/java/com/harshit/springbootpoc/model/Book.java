package com.harshit.springbootpoc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.harshit.springbootpoc.dto.BookCategoryRequest;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer bookId;

    private String title;

    private String description;

    @Column(name = "photo_path")
    private String photoPath;

    private String synopsis;

    @Column(name = "total_pages")
    private Integer totalPages;

    @Column(name = "approximate_minutes")
    private Integer approximateMinutes;

    @Column(name = "published_date")
    private LocalDate publishedDate;

    @Column(name = "number_of_reads")
    private Integer numberOfReads;

    @Column(name = "audio_availability")
    private Boolean audioAvailability;

    @ManyToMany
    @JoinTable(
            name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @JsonIgnore
    private Set<Category> bookCategory;

    @OneToMany(mappedBy = "book")
    private Set<BookHighlights> bookHighlights;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private Set<UserLibrary> userLibrary;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> bookAuthor;

    public Book(BookCategoryRequest bookCategoryRequest){
        this.bookId = bookCategoryRequest.getBookId();
        this.title = bookCategoryRequest.getTitle();
        this.description = bookCategoryRequest.getDescription();
        this.photoPath=bookCategoryRequest.getPhotoPath();
        this.synopsis=bookCategoryRequest.getSynopsis();
        this.totalPages=bookCategoryRequest.getTotalPages();
        this.approximateMinutes = bookCategoryRequest.getApproximateMinutes();
        this.publishedDate = bookCategoryRequest.getPublishedDate();
        this.numberOfReads=bookCategoryRequest.getNumberOfReads();
        this.audioAvailability = bookCategoryRequest.getAudioAvailability();
        this.bookCategory = bookCategoryRequest.getBookCategory();
        this.bookAuthor=bookCategoryRequest.getBookAuthor();
    }

}
