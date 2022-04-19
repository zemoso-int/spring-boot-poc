package com.harshit.springbootpoc.service;

import com.harshit.springbootpoc.dto.UserResponse;
import com.harshit.springbootpoc.exception.BookNotFoundException;
import com.harshit.springbootpoc.exception.UserNotFoundException;

public interface UserService {
    UserResponse getReadingBooksBasedOnUser(Integer userId) throws UserNotFoundException;

    UserResponse getFinishedBooksBasedOnUser(Integer userId)throws UserNotFoundException;

    @SuppressWarnings("SameReturnValue")
    boolean addBookToUsersLibrary(Integer userId, Integer bookId)throws BookNotFoundException;

    boolean changeStatusOfBook(Integer userId, Integer bookId)throws UserNotFoundException;

}
