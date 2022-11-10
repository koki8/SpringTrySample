package com.example.SpringTrySample.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookForm {
    @NotNull
    private Integer id;

    @NotBlank
    private String bookName;

    @NotNull
    private Integer volumeNum;

    @NotBlank
    private String authorName;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) //    このアノテーションをつけないと日付データ受け取れない 2022-08-22形式でデータ受け取る
    private LocalDate publishedDate;

    //「登録」or「更新」判定用
    @NotNull
    private Boolean newBook;
}
