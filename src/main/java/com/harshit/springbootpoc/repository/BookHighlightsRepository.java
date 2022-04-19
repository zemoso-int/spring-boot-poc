package com.harshit.springbootpoc.repository;

import com.harshit.springbootpoc.model.BookHighlights;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookHighlightsRepository extends JpaRepository<BookHighlights, Integer> {
}
