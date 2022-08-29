package com.example.MybatisTrySample.dao;

import com.example.MybatisTrySample.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BookDao {
    //ID検索
    Optional<Book> findById(Book book);

    //全て本取得
    List<Book> findAll();

    //本の登録
    void insertBook(Book book);

    //本の更新
    boolean updateBook(Book book);

    //本の削除
    boolean deleteBook(Book book);

}
