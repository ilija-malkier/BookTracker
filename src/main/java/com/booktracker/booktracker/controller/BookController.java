package com.booktracker.booktracker.controller;

import com.booktracker.booktracker.model.Book;
import com.booktracker.booktracker.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookRepository bookRepository;

    @GetMapping("/{bookId}")
    public String getBook(@PathVariable("bookId") String bookId){
        Optional<Book> byId = bookRepository.findById(bookId);

        return (byId.isPresent())? byId.get().toString() : "NOT FOUND";
    }
}
