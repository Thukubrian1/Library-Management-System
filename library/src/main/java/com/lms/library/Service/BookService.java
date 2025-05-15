
package com.lms.library.Service;

import java.util.List;
import com.lms.library.Model.Book;
import org.springframework.stereotype.Service;

@Service
public interface BookService{

    public List<Book> getAllBooks();

    public List<Book> getBookById(int id);

    public Book addBook(Book book);

    public void updateBook(int id);

    public void deleteBook(int id);

    
    
}