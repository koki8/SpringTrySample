package com.example.MybatisTrySample.controller;

import com.example.MybatisTrySample.entity.Book;
import com.example.MybatisTrySample.form.BookForm;
import com.example.MybatisTrySample.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @ModelAttribute
    public BookForm setUpForm(){
        BookForm bookForm = new BookForm();
        return bookForm;
    }

    @RequestMapping("/search")
    public String index(BookForm bookForm, String showList,  Model model){

        model.addAttribute("title", "本屋さん");

        if(bookForm.getId() != null){
            Book book = bookService.findById(bookForm.getId());
            model.addAttribute("book",book);
        }

        if(showList != null){
            List<Book> bookList = bookService.getBookList();
            model.addAttribute("bookList", bookList);
        }

        return "index";
    }

    @PostMapping("/insert")
    public String insert(BookForm bookForm, Model model){

        Book book = new Book(5, "どすこいおむすびくん", 3, "おむすび伯爵", LocalDate.now());
//        book.setBookName(bookForm.getBookName());
//        book.setVolumeNum(bookForm.getVolumeNum());
//        book.setAuthorName(bookForm.getAuthorName());
//        book.setPublishedDate(bookForm.getPublishedDate());
        bookService.insertBook(book);

        return "redirect:/book/search";
    }
}
