package com.harshit.springbootpoc.repository;

import com.harshit.springbootpoc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
