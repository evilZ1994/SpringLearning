package com.ishavanti.service;

import com.ishavanti.bean.Book;

public interface BookService {
    void addBook(Book book);

    void updateBookByIsbn(Book book);

    void findBookByIsbn(String isbn);
}
