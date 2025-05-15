package com.lms.library.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lms.library.Model.Book;
import com.lms.library.Repository.BookRepository;


@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    
    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getBookById(int id) {
        return bookRepository.getBookById(id);
    }

    @Override
    public void updateBook(int id) {
        bookRepository.updateBook(id);
    }

    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
}
