package com.harshit.springbootpoc.repository;

import com.harshit.springbootpoc.model.UserLibrary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLibraryRepository extends JpaRepository<UserLibrary,Integer> {
}
