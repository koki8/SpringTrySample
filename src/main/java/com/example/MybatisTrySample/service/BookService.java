package com.example.MybatisTrySample.service;

import com.example.MybatisTrySample.dao.BookDao;
import com.example.MybatisTrySample.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {


    private final BookDao bookDao;

    BookService(BookDao bookdao){
        this.bookDao = bookdao;
    }

    public Optional<Book> findById(Integer id){
        Book book = new Book();
        book.setId(id); //検索用のDTOに入力値から受け取ったidをセットしている
        return bookDao.findById(book);
    }

    public List<Book> getBookList(){
        return bookDao.findAll();
    }

    public void insertBook(Book book){
        bookDao.insertBook(book);
    }

    public boolean updateBook(Book book) { return  bookDao.updateBook(book);}
}
