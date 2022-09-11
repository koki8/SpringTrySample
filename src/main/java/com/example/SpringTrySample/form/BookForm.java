package com.example.SpringTrySample.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookForm {
    private Integer id;
    private String bookName;
    private Integer volumeNum;
    private String authorName;

//    このアノテーションをつけないと日付データ受け取れない 2022-08-22形式でデータ受け取る
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate publishedDate;

    //「登録」or「更新」判定用
    private Boolean newBook;
}
