package com.example.SpringTrySample.controller;

import com.example.SpringTrySample.entity.Book;
import com.example.SpringTrySample.service.BookService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


public class BookControllerTest {

    private MockMvc mockMvc;

    @Mock
    BookService bookService;

    @InjectMocks
    BookController bookController;

    AutoCloseable closeable;

//    BookService bookService = mock(BookService.class);
//    BookController bookController = new BookController(bookService);

    Book testBook1 = new Book(1, "HUNTER X HUNTER",36,"冨樫義博", LocalDate.parse("2018-10-04"));
    Book testBook2 = new Book(2, "ベルセルク",40,"三浦健太郎", LocalDate.parse("2018-09-28"));


    @BeforeEach
    public void setup(){

        /**
         * openMocks(this)で@Mockアノテーションでモックやスパイを初期化する
         * initMocks(this)は非推奨になっている
         */
        closeable = MockitoAnnotations.openMocks(this);

        // MockMvcオブジェクトにテスト対象メソッドを設定
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @AfterEach
    public void tearDown() throws Exception {
        closeable.close();
    }

    /**
     * BookControllerクラスのindexメソッドを確認するためのテスト
     * @throws Exception
     */
    @Test
    public void index() throws Exception{
        List<Book> mockitBookList = List.of(testBook1,testBook2);

        doReturn(mockitBookList).when(bookService).getBookList();

        this.mockMvc.perform(get("/book/search"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

}