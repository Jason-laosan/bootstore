package org.bookstrore.service;


import org.bookstrore.entity.Book;
import org.bookstrore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

}