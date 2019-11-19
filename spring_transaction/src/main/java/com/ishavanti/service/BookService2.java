package com.ishavanti.service;

import com.ishavanti.bean.Book;

public interface BookService2 {
    void addBook(Book book);

    void updateBookByIsbn(Book book);

    void findBookByIsbn(String isbn);
}
