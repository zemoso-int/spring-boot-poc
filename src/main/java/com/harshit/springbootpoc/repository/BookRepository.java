package com.harshit.springbootpoc.repository;

import com.harshit.springbootpoc.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByTitleContains(String title);
    
    List<Book> findByBookAuthorFirstNameOrBookAuthorLastNameContains(String firstName, String lastName);
}
