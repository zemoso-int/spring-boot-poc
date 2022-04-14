package com.harshit.springbootpoc.dto;

import com.harshit.springbootpoc.model.Author;
import com.harshit.springbootpoc.model.Category;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookCategoryRequest {

    private Integer bookId;

    @NonNull
    private String title;

    @NonNull
    private String description;

    @NonNull
    private String photoPath;

    @NonNull
    private String synopsis;

    @NonNull
    private Integer totalPages;

    @NonNull
    private Integer approximateMinutes;

    @NonNull
    private LocalDate publishedDate;

    private Integer numberOfReads;

    @NonNull
    private Boolean audioAvailability;

    @NonNull
    private Set<Author> bookAuthor;

    @NonNull
    private Set<Category> bookCategory;

}
