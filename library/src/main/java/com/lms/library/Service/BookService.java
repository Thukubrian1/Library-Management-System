package com.lms.library.Service;


import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.lms.library.DTO.BookDTO;

@Service
public interface BookService{


    List<BookDTO> getAllBooks(BookDTO bookDTO);

    Optional<BookDTO> getBookById(int id);

    BookDTO addBook(BookDTO bookDTO);

    BookDTO updateBook(BookDTO bookDTO);

    void deleteBookById(int id);
}