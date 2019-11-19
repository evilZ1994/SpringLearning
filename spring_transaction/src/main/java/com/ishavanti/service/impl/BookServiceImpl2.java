package com.ishavanti.service.impl;

import com.ishavanti.bean.Book;
import com.ishavanti.service.BookService2;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class BookServiceImpl2 implements BookService2 {
    public void addBook(Book book) {

    }

    public void updateBookByIsbn(Book book) {

    }

    public void findBookByIsbn(String isbn) {

    }
}
