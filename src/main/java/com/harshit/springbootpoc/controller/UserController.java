package com.harshit.springbootpoc.controller;

import com.harshit.springbootpoc.dto.UserResponse;
import com.harshit.springbootpoc.exception.BookNotFoundException;
import com.harshit.springbootpoc.exception.UserNotFoundException;
import com.harshit.springbootpoc.service.UserService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "{userId}/books/currently-reading")
    public UserResponse getReadingBooksByUser(@PathVariable("userId") Integer userId) throws UserNotFoundException {
        return userService.getReadingBooksBasedOnUser(userId);
    }

    @GetMapping(value = "{userId}/books/finished")
    public UserResponse getCompletedBooksByUser(@PathVariable("userId") Integer userId) throws UserNotFoundException {
        return userService.getFinishedBooksBasedOnUser(userId);
    }

    @PostMapping(value = "{userId}/books/{bookId}")
    public boolean addBookToUsersLibrary(@PathVariable("userId") Integer userId, @PathVariable("bookId") Integer bookId) throws BookNotFoundException {
        return userService.addBookToUsersLibrary(userId,bookId);
    }

    @PutMapping(value = "{userId}/books/{bookId}/status")
    public boolean changeBookStatusOfUser(@PathVariable("userId") Integer userId,@PathVariable("bookId") Integer bookId) throws UserNotFoundException {
        return userService.changeStatusOfBook(userId, bookId);
    }

}
