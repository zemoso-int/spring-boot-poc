package com.harshit.springbootpoc.service;

import com.harshit.springbootpoc.dto.UserResponse;
import com.harshit.springbootpoc.exception.BookNotFoundException;
import com.harshit.springbootpoc.exception.UserNotFoundException;
import com.harshit.springbootpoc.model.Author;
import com.harshit.springbootpoc.model.Book;
import com.harshit.springbootpoc.model.User;
import com.harshit.springbootpoc.model.UserLibrary;
import com.harshit.springbootpoc.repository.UserLibraryRepository;
import com.harshit.springbootpoc.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserLibraryRepository userLibraryRepository;

    @Mock
    private BookService bookService;

    @InjectMocks
    private UserBooksService userBooksService;

    @Test
    void getReadingBooksBasedOnUserTest(){
        Set<Author> authors = new HashSet<>();
        authors.add(new Author(1,"James","Harden","Science Friction"));
        authors.add(new Author(2,"Gon","Killua","Manga"));
        Book book = new Book(1,"Example","Example description","","Example Synopsis",300,20, LocalDate.parse("2019-03-01"),1500,true,null,null,null,authors);
        Book book1 = new Book(2,"Example 2","Example description 2","","Example Synopsis 2",500,20, LocalDate.parse("2019-03-01"),1200,true,null,null,null,authors);
        UserLibrary userLibrary = new UserLibrary(1,book,null,false);
        UserLibrary userLibrary1 = new UserLibrary(2,book1,null,true);
        Set<UserLibrary> userLibraries = new HashSet<>();
        userLibraries.add(userLibrary);
        userLibraries.add(userLibrary1);
        User user=new User(1,"Eric","Blots","ericblots.example.com",userLibraries);
        Mockito.when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        UserResponse userResponse = userBooksService.getReadingBooksBasedOnUser(1);
        assertEquals(1,userResponse.getUserLibrary().size());
        List<UserLibrary> outputUserLibraries = userResponse.getUserLibrary();
        for(UserLibrary library: outputUserLibraries){
            assertEquals(false,library.getCompleted());
        }
    }

    @Test
    void getFinishedBooksBasedOnUserTest(){
        Set<Author> authors = new HashSet<>();
        authors.add(new Author(1,"James","Harden","Science Friction"));
        authors.add(new Author(2,"Gon","Killua","Manga"));
        Book book = new Book(1,"Example","Example description","","Example Synopsis",300,20, LocalDate.parse("2019-03-01"),1500,true,null,null,null,authors);
        UserLibrary userLibrary = new UserLibrary(1,book,null,true);
        Set<UserLibrary> userLibraries = new HashSet<>();
        userLibraries.add(userLibrary);
        User user=new User(1,"Eric","Blots","ericblots.example.com",userLibraries);
        Mockito.when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        UserResponse userResponse = userBooksService.getFinishedBooksBasedOnUser(1);
        assertEquals(1,userResponse.getUserLibrary().size());
        List<UserLibrary> outputUserLibraries = userResponse.getUserLibrary();
        for(UserLibrary library: outputUserLibraries){
            assertEquals(true,library.getCompleted());
        }
    }

    @Test
    void TestAddBookToUsersLibrary(){
        Set<Author> authors = new HashSet<>();
        authors.add(new Author(1,"James","Harden","Science Friction"));
        authors.add(new Author(2,"Gon","Killua","Manga"));
        Book book = new Book(1,"Example","Example description","","Example Synopsis",300,20, LocalDate.parse("2019-03-01"),1500,true,null,null,null,authors);
        Mockito.when(bookService.getBookById(1)).thenReturn(book);
        User user=new User(1,"Eric","Blots","ericblots.example.com",null);
        UserLibrary userLibrary = new UserLibrary(1,book,user,false);
        Mockito.when(userLibraryRepository.save(Mockito.any())).thenReturn(userLibrary);
        assertTrue(userBooksService.addBookToUsersLibrary(1, 1));
    }

    @Test
    void TestChangeStatusOfBook(){
        Set<Author> authors = new HashSet<>();
        authors.add(new Author(1,"James","Harden","Science Friction"));
        authors.add(new Author(2,"Gon","Killua","Manga"));
        Book book = new Book(1,"Example","Example description","","Example Synopsis",300,20, LocalDate.parse("2019-03-01"),1500,true,null,null,null,authors);
        UserLibrary userLibrary = new UserLibrary(1,book,null,false);
        Set<UserLibrary> userLibraries = new HashSet<>();
        userLibraries.add(userLibrary);
        User user=new User(1,"Eric","Blots","ericblots.example.com",userLibraries);
        Mockito.when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        Mockito.when(userLibraryRepository.save(Mockito.any())).thenReturn(userLibrary);
        assertTrue(userBooksService.changeStatusOfBook(1, 1));
    }

    @Test
    void testGetReadingBooksBasedOnUserIsNull(){
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,()->{
            userBooksService.getReadingBooksBasedOnUser(1);
        });
    }

    @Test
    void testGetFinishedBooksBasedOnUserIsNull(){
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,()->{
            userBooksService.getFinishedBooksBasedOnUser(1);
        });
    }

    @Test
    void testBookIsNull(){
        Mockito.when(bookService.getBookById(1)).thenReturn(null);
        assertThrows(BookNotFoundException.class,()->{
            userBooksService.addBookToUsersLibrary(1,1);
        });
    }

    @Test
    void testChangeStatusOfBookIsNull(){
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,()->{
            userBooksService.changeStatusOfBook(1,1);
        });
    }

    @Test
    void TestChangeStatusOfNotBookFound(){
        Set<Author> authors = new HashSet<>();
        authors.add(new Author(1,"James","Harden","Science Friction"));
        authors.add(new Author(2,"Gon","Killua","Manga"));
        Book book = new Book(1,"Example","Example description","","Example Synopsis",300,20, LocalDate.parse("2019-03-01"),1500,true,null,null,null,authors);
        UserLibrary userLibrary = new UserLibrary(1,book,null,false);
        Set<UserLibrary> userLibraries = new HashSet<>();
        userLibraries.add(userLibrary);
        User user=new User(1,"Eric","Blots","ericblots.example.com",userLibraries);
        Mockito.when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        assertFalse(userBooksService.changeStatusOfBook(1, 2));
    }



}
