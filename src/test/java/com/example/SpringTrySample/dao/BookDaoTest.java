package com.example.SpringTrySample.dao;

import com.example.SpringTrySample.entity.Book;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.destination.Destination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest  // @SpringBootConfigurationがついたクラスが自動検出される
@ActiveProfiles("test")  // application-test.ymlの内容が読み込まれる
@Rollback  // @Transactionalがあればいらない。。？
@Transactional //　トランザクション管理される
public class BookDaoTest {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private DataSource dataSource;

    Destination dest;

    private final Operation RESET_DATA = Operations.deleteAllFrom("libraryschema.booktable");
    private final Operation INSERT_BOOKTABLE = Operations.insertInto("libraryschema.booktable")
            .columns("id","book_name","volume_num", "author_name", "published_date")
            .values(1, "HUNTER X HUNTER",36,"冨樫義博","2018-10-04")
            .values(2, "ベルセルク",40,"三浦健太郎","2018-09-28")
            .build();

    public void dbSetup(Operation operation) {
        dest = new DataSourceDestination(dataSource);
        DbSetup dbSetup = new DbSetup(dest, operation);
        dbSetup.launch();
    }

    @BeforeEach
    public void setUp(){
        dbSetup(sequenceOf(RESET_DATA, INSERT_BOOKTABLE));
    }

    @AfterEach
    public void tearDown(){
        dbSetup(sequenceOf(RESET_DATA));
    }

    @Nested
    public class findById{
        @Test
        @DisplayName("findById_書籍情報の取得成功")
        public void findByIdSuccess() {
            Book checkBook = new Book();
            checkBook.setId(1);
            Optional<Book> actualBookOpt = bookDao.findById(checkBook);
            Book actualBook = actualBookOpt.get();

            assertThat(actualBook).extracting("id").isEqualTo(1);
            assertThat(actualBook).extracting("bookName").isEqualTo("HUNTER X HUNTER");
            assertThat(actualBook).extracting("volumeNum").isEqualTo(36);
            assertThat(actualBook).extracting("authorName").isEqualTo("冨樫義博");
            assertThat(actualBook).extracting("publishedDate").isEqualTo(LocalDate.parse("2018-10-04"));
        }

        @Test
        @DisplayName("findById_書籍情報の取得失敗")
        public void findByIdFailure() {
            Book checkBook = new Book();
            checkBook.setId(5);

            Optional<Book> actualBookOpt = bookDao.findById(checkBook);

            assertThat(actualBookOpt).isEmpty();
        }

    }

    @Nested
    public class findAll{
        @Test
        @DisplayName("findAll_書籍情報一覧の取得成功")
        public void findAllSuccess() {
            List<Book> actualBookList = bookDao.findAll();
            assertThat(actualBookList).isNotNull();
            assertThat(actualBookList).extracting("id").allMatch(i -> Arrays.asList(1,2).contains(i));
            assertThat(actualBookList).extracting("bookName").allMatch(i -> Arrays.asList("HUNTER X HUNTER","ベルセルク").contains(i));
            assertThat(actualBookList).extracting("volumeNum").allMatch(i -> Arrays.asList(36,40).contains(i));
            assertThat(actualBookList).extracting("authorName").allMatch(i -> Arrays.asList("冨樫義博","三浦健太郎").contains(i));
            assertThat(actualBookList).extracting("publishedDate").allMatch(i -> Arrays.asList(LocalDate.parse("2018-10-04"),LocalDate.parse("2018-09-28")).contains(i));
        }

    }

    @Nested
    public class insertBook{
        @Test
        @DisplayName("insertBook_書籍情報の追加成功")
        public void insertBookSuccess() {
            final Table table = new Table(dataSource, "libraryschema.booktable");
            Changes changes = new Changes(table);

            changes.setStartPointNow();

            Book setBook = new Book();
            setBook.setBookName("HUNTER X HUNTER");
            setBook.setVolumeNum(36);
            setBook.setAuthorName("冨樫義博");
            setBook.setPublishedDate(LocalDate.parse("2018-10-04"));
            bookDao.insertBook(setBook);

            changes.setEndPointNow();
            org.assertj.db.api.Assertions.assertThat(changes)
                    .hasNumberOfChanges(1)   //1レコード変更された確認　ここでどうしても失敗する。なぜか変更箇所が0
                    .ofModificationOnTable("libraryschema.booktable");

        }

        @Test
        @DisplayName("insertBook_書籍情報の追加失敗")
        public void insertBookFailure(){
            Book setBook = new Book();
            setBook.setBookName("HUNTER X HUNTER");
            setBook.setVolumeNum(36);
            setBook.setAuthorName(null);
            setBook.setPublishedDate(LocalDate.parse("2018-10-04"));

            assertThatThrownBy(() -> {
                bookDao.insertBook(setBook);
            }).isInstanceOf(DataIntegrityViolationException.class);

        }

    }

    @Nested
    public class updateBook{
        /**
         * なぜかテスト実行すると、ずっとぐるぐる周り続けてテストが終わらない。。
         */
        @Test
        @DisplayName("updateBook_書籍情報の更新成功")
        public void updateBookSuccess() {
            Changes changes = new Changes(dataSource);

            changes.setStartPointNow();
            Book book = new Book(1, "UpdateTest",36,"冨樫義博",LocalDate.parse("2018-10-04"));
            Boolean check =  bookDao.updateBook(book);

            changes.setEndPointNow();
            org.assertj.db.api.Assertions.assertThat(changes)
                    .hasNumberOfChanges(1);


        }

        @Test
        @DisplayName("updateBook_書籍情報の更新失敗")
        public void updateBookFailure() {
            Book book = new Book(4, "UpdateTest",36,"冨樫義博",LocalDate.parse("2018-10-04"));
            Boolean check =  bookDao.updateBook(book);
            assertThat(check).isFalse();
        }
    }

    @Nested
    public class deleteBook{

        /**
         * なぜかテスト実行すると、ずっとぐるぐる周り続けてテストが終わらない。。
         */
        @Test
        @DisplayName("deleteBook_書籍情報の削除成功")
        public void deleteBookSuccess() {
            Changes changes = new Changes(dataSource);

            changes.setStartPointNow();
            Book book = new Book();
            book.setId(1);
            Boolean check =  bookDao.deleteBook(book);
            changes.setEndPointNow();
            org.assertj.db.api.Assertions.assertThat(changes)
                    .hasNumberOfChanges(1);

        }

        @Test
        @DisplayName("deleteBook_書籍情報の削除失敗")
        public void deleteBookFailure(){
            Book book = new Book();
            book.setId(3);
            Boolean check =  bookDao.deleteBook(book);
            assertThat(check).isFalse();
        }
    }

}