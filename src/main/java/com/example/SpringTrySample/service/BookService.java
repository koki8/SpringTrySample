package com.example.SpringTrySample.service;

import com.example.SpringTrySample.dao.BookDao;
import com.example.SpringTrySample.entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
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

    Logger logger = LoggerFactory.getLogger(BookService.class);

    public Optional<Book> findById(Integer id){
        Book book = new Book();
        book.setId(id); //検索用のDTOに入力値から受け取ったidをセットしている
        return bookDao.findById(book);
    }

    public List<Book> getBookList(){
        logger.info("本のリストを抽出します");
        return bookDao.findAll();
    }

    public void insertBook(Book book){
       try{
           bookDao.insertBook(book);
       } catch (DuplicateKeyException e){
           logger.error("一意制約違反です。");
           throw e;
       }


    }

    public boolean updateBook(Book book) { return  bookDao.updateBook(book);}

    public boolean deleteBook(Integer id){
        Book book = new Book();
        book.setId(id);
        return bookDao.deleteBook(book);}
}
