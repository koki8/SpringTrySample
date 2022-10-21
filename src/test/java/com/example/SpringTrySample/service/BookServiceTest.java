package com.example.SpringTrySample.service;

import com.example.SpringTrySample.dao.BookDao;
import com.example.SpringTrySample.entity.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)  // JUnit5でMockito使うには必要
@SpringBootTest
@ActiveProfiles("test")
@Rollback
@Transactional
public class BookServiceTest {

    /**
     * https://qiita.com/gitcho/items/5ce478ec8e29bbddc152
     * https://ytnk531.hatenablog.com/entry/2018/08/11/120715
     * こっちの方法ではモック化からインスタンスの注入に失敗した
     * bookServiceのメソッドを実行しても、スタブが返ってこない
     */
//    @Mock //モック（スタブ）に置き換えたいインスタンスに定義。全てのメソッドがモックになる
//    private BookDao bookDao;
//    @InjectMocks //@Mockでモックにしたインスタンスの注入先となるインスタンスに定義
//    private BookService bookService;

    /**
     * https://www.tsuchiya.blog/mockito-basic/
     * mockメソッドを使って、BookDaoをモック化したあと、
     * BookServiceのインスタンスをBookDaoを渡して生成
     * こっちの方法では、bookServiceを問題なく実装できたが、なかば無理矢理感がある。。
     */
    BookDao bookDao = mock(BookDao.class);
    BookService bookService = new BookService(bookDao);

    Book testBook1 = new Book(1, "HUNTER X HUNTER",36,"冨樫義博", LocalDate.parse("2018-10-04"));
    Book testBook2 = new Book(2, "ベルセルク",40,"三浦健太郎", LocalDate.parse("2018-09-28"));

    @DisplayName("BookService.findByIdが正しくBookDao.findByIdメソッドに値を渡している")
    @Test
    public void findById() {

        //　ダミーデータの用意
        Optional<Book> mockBookOpt  = Optional.of(testBook1);

        Book checkBook = new Book();
        checkBook.setId(1);

        /**
         * https://qiita.com/gitcho/items/5ce478ec8e29bbddc152
         * こっちのスタブの書き方は、UnfinishedStubbingExceptionが発生してうまくいかなかった
         */
//        Mockito.when(this.bookDao.findById(checkBook)) //このモックを呼び出したとき、UnfinishedStubbingExceptionが発生する
//                .thenReturn(mockBookOpt);     //このデータを返すようにする

        /**
         * https://www.tsuchiya.blog/mockito-basic/
         * こっちのスタブの書き方であれば
         * UnfinishedStubbingExceptionは発生しなかった
         */
        doReturn(mockBookOpt).when(bookDao).findById(checkBook);

        Optional<Book> bookOpt = bookService.findById(1);
        Book actualBook = bookOpt.get();
        assertThat(actualBook).extracting("id").isEqualTo(1);

        verify(bookDao,times(1)).findById(checkBook);
    }

    @DisplayName("BookService.getBookListが正しくBookDao.findAllメソッドを呼んでいる")
    @Test
    public void getBookList() {

        List<Book> mockitBookList = List.of(testBook1,testBook2);

//        Mockito.when(this.bookDao.findAll()) //このモックを呼び出したとき
//                .thenReturn(mockitBookList);     //このデータを返すようにする
//
        doReturn(mockitBookList).when(bookDao).findAll();

        List<Book> actualBookList = bookService.getBookList();
        assertThat(actualBookList).isNotNull();
        assertThat(actualBookList).extracting("id").allMatch(i -> Arrays.asList(1,2).contains(i));
        assertThat(actualBookList).extracting("bookName").allMatch(i -> Arrays.asList("HUNTER X HUNTER","ベルセルク").contains(i));
        assertThat(actualBookList).extracting("volumeNum").allMatch(i -> Arrays.asList(36,40).contains(i));
        assertThat(actualBookList).extracting("authorName").allMatch(i -> Arrays.asList("冨樫義博","三浦健太郎").contains(i));
        assertThat(actualBookList).extracting("publishedDate").allMatch(i -> Arrays.asList(LocalDate.parse("2018-10-04"),LocalDate.parse("2018-09-28")).contains(i));

        verify(bookDao,times(1)).findAll();
    }

    @DisplayName("BookService.insertBookが正しくBookDao.insertBookメソッドを呼んでいる")
    @Test
    public void insertBook() {

        doNothing().when(bookDao).insertBook(testBook1);
        bookService.insertBook(testBook1);

        /**
         * https://www.tsuchiya.blog/mockito-basic/
         * https://kuxumarin.hatenablog.com/entry/2018/10/29/004708
         * verifyメソッドでbookDao.insertが実行されているかを確認した
         */
        verify(bookDao,times(1)).insertBook(testBook1);
    }

    @DisplayName("BookService.updateBookが正しくBookDao.updateBookメソッドを呼んでいる")
    @Test
    public void updateBook() {

        Book updateBook = new Book(1, "UpdateBookTest",36,"冨樫義博", LocalDate.parse("2018-10-04"));

        doReturn(true).when(bookDao).updateBook(updateBook);

        assertThat(bookService.updateBook(updateBook)).isTrue();
        verify(bookDao,times(1)).updateBook(updateBook);
    }


    @DisplayName("BookService.deleteBookが正しくBookDao.deleteBookメソッドを呼んでいる")
    @Test
    public void deleteBook() {

        Book checkBook = new Book();
        checkBook.setId(1);

        doReturn(true).when(bookDao).deleteBook(checkBook);

        assertThat(bookService.deleteBook(1)).isTrue();
        verify(bookDao,times(1)).deleteBook(checkBook);
    }
}