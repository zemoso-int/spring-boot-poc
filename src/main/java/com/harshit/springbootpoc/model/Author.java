package com.harshit.springbootpoc.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "author")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Integer authorId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "area_of_interest")
    private String areaOfInterest;

    @ManyToMany(mappedBy = "bookAuthor")
    @JsonIgnore
    private Set<Book> books;

    public Author(Integer authorId, String firstName, String lastName, String areaOfInterest) {
        this.authorId = authorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.areaOfInterest = areaOfInterest;
    }

}
