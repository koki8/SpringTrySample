package com.example.SpringTrySample.dao;

import com.example.SpringTrySample.entity.Book;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.destination.Destination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;


import javax.sql.DataSource;

import java.util.Optional;

import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class}
)
class BookDaoTest {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private DataSource dataSource;

    Destination dest = new DataSourceDestination(dataSource);

    private final Operation RESET_DATA = Operations.deleteAllFrom("libraryschema.booktable");
    private final Operation INSERT_BOOKTABLE = Operations.insertInto("libraryschema.booktable")
            .columns("id","book_name","volume_num", "author_name", "published_date")
            .values(1, "HUNTER X HUNTER",36,"冨樫義博","'2018-10-04")
            .values(2, "ベルセルク",40,"三浦健太郎","2018-09-28")
            .build();

//    private void dbSetup(Operation operation) {
//        Destination destination = new DataSourceDestination(dataSource);
//        DbSetup dbSetup = new DbSetup(destination, operation);
//        dbSetup.launch();
//    }

    @BeforeEach
    void setUp(){
        Operation ops = sequenceOf(RESET_DATA, INSERT_BOOKTABLE);
        DbSetup dbSetup = new DbSetup(dest, ops);
//        dbSetup(Operations.sequenceOf(RESET_DATA));
    }

//    @AfterEach
//    void tearDown(){
//        dbSetup(sequenceOf(RESET_DATA));
//    }

//    @Test
//    void findById() {
//        dbSetup(Operations.sequenceOf(INSERT_BOOKTABLE));
//        Book checkBook = new Book();
//        checkBook.setId(1);
//        Optional<Book> actualBook = bookDao.findById(checkBook);
//        assertThat(actualBook)
//                .isNotEmpty()
//                .isNotNull();
//    }

    @Test
    void findAll() {
    }

    @Test
    void insertBook() {
    }

    @Test
    void updateBook() {
    }

    @Test
    void deleteBook() {
    }
}