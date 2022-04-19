package com.harshit.springbootpoc.model;

import com.harshit.springbootpoc.dto.CategoryRequest;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @ManyToMany(mappedBy = "bookCategory")
    Set<Book> books;

    public Category(CategoryRequest categoryRequest){
        this.categoryName = categoryRequest.getCategoryName();
    }

}
