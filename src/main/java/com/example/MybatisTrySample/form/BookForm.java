package com.example.MybatisTrySample.form;

import lombok.Data;

@Data
public class BookForm {
    private Integer id;
    private String bookName;
    private Integer volumeNum;
    private String authorName;
    private Data publishedDate;
}
