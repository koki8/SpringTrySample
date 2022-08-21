package com.example.MybatisTrySample.dao;

import com.example.MybatisTrySample.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookDao {
    Book findById(Book book);

    List<Book> findAll();


}
