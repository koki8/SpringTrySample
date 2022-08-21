package com.example.MybatisTrySample.service;

import com.example.MybatisTrySample.dao.BookDao;
import com.example.MybatisTrySample.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {


    private BookDao bookDao;

    BookService(BookDao bookdao){
        this.bookDao = bookdao;
    }

    public Book findById(Integer id){
        Book book = new Book();
        book.setId(id); //なんで？
        return bookDao.findById(book);
    }

    public List<Book> getBookList(){
        return bookDao.findAll();
    }
}
