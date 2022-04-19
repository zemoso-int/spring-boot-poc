package com.harshit.springbootpoc.repository;

import com.harshit.springbootpoc.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
	Author findByFirstNameAndLastName(String firstName,String lastName);
}
