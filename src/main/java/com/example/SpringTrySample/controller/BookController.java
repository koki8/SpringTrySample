package com.example.SpringTrySample.controller;

import com.example.SpringTrySample.entity.Book;
import com.example.SpringTrySample.form.BookForm;
import com.example.SpringTrySample.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @ModelAttribute
    public BookForm setUpForm(){
        BookForm bookform = new BookForm();
        return bookform;
    }

    @RequestMapping("/search")
    public String index(BookForm bookForm, String showList,  Model model){

        //新規登録設定
        bookForm.setNewBook(true);

        model.addAttribute("title", "本屋さん");
        model.addAttribute("formTitle", "登録用フォーム");

        if(bookForm.getId() != null){
            Optional<Book> bookOpt = bookService.findById(bookForm.getId());
            if(bookOpt.isPresent()){
                Book book = bookOpt.get();
                model.addAttribute("book",book);
            }
        }

        if(showList != null){
            List<Book> bookList = bookService.getBookList();
            model.addAttribute("bookList", bookList);
        }

        return "index";
    }

    @PostMapping("/insert")
    public String insert(BookForm bookForm, Model model){

//        Book book = makeBook(bookForm);
        Book book = new Book(1, "羅生門",1, "芥川龍之介", LocalDate.now());
        bookService.insertBook(book);

        return "redirect:/book/search";
    }

    @GetMapping("/{id}")
    public String showUpdate(BookForm bookForm, @PathVariable Integer id, Model model){
        Optional<Book> bookOpt = bookService.findById(id);

        //BookをBookFormに詰め直す
        Optional<BookForm> bookFormOpt = bookOpt.map(t -> makeBookForm(t));
        //BookFormがnullでなければ中身を取り出す
        if(bookFormOpt.isPresent()){
            bookForm = bookFormOpt.get();
        }
        makeUpdateModel(bookForm, model);
        return "index";
    }

    /** idをKeyにしてデータを更新 **/
    @PostMapping("/update")
    public String updateBook(BookForm bookForm, Model model){
        Book book = makeBook(bookForm);
        bookService.updateBook(book);
        return "redirect:/book/search";
    }

    @PostMapping("/delete")
    public String deleteBook(BookForm bookForm, Model model){

        bookService.deleteBook(bookForm.getId());
        return "redirect:/book/search";
    }


/** 以下部品作成　**/
    //更新用のModel作成
    private void makeUpdateModel(BookForm bookForm, Model model){
        model.addAttribute("id", bookForm.getId());
        bookForm.setNewBook(false);
        model.addAttribute("bookForm",bookForm);
        model.addAttribute("formTitle","更新用フォーム");
    }

    //【以下はFormとDTOの詰め直し】

    //BookFormからBookに詰め直して戻り値として返す
    private Book makeBook(BookForm bookForm){
        Book book = new Book();
        book.setId(bookForm.getId());
        book.setBookName(bookForm.getBookName());
        book.setVolumeNum(bookForm.getVolumeNum());
        book.setAuthorName(bookForm.getAuthorName());
        book.setPublishedDate(bookForm.getPublishedDate());
        return book;
    }

    //BookからBookFormに詰め直して戻り値として返す
    private BookForm makeBookForm(Book book){
        BookForm bookForm = new BookForm();
        bookForm.setId(book.getId());
        bookForm.setBookName(book.getBookName());
        bookForm.setVolumeNum(book.getVolumeNum());
        bookForm.setAuthorName(book.getAuthorName());
        bookForm.setPublishedDate(book.getPublishedDate());
        bookForm.setNewBook(false);
        return bookForm;
    }

}
