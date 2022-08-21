package com.example.MybatisTrySample.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Book {
    private Integer id;
    private String bookName;
    private Integer volumeNum;
    private String authorName;
    private LocalDate publishedDate;
}
