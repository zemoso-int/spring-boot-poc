package com.harshit.springbootpoc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "book_highlights")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookHighlights {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_highlights_id")
    private Integer bookHighlightsId;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonIgnore
    private Book book;

    @Column(name = "chapter")
    private Integer chapter;

    @Column(name = "chapter_title")
    private String chapterTitle;

    @Column(name = "chapter_highlights")
    private String chapterHighlight;

}
