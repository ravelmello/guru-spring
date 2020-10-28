package com.course.springguru.controllers;

import com.course.springguru.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author: Ravel Mello
 * Created at: 28/10/2020
 */

@Controller
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @RequestMapping(value = "/books")
    public String getBooks(Model model){
        model.addAttribute("books",bookRepository.findAll());
        return "books/list";
    }
}
