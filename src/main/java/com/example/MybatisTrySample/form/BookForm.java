package com.example.MybatisTrySample.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookForm {
    private Integer id;
    private String bookName;
    private Integer volumeNum;
    private String authorName;
    private LocalDate publishedDate;
}
