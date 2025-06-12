package com.lms.library.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.lms.library.DTO.BookDTO;
import com.lms.library.Model.Author;
import com.lms.library.Model.Book;
import com.lms.library.Repository.AuthorRepository;
import com.lms.library.Repository.BookRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;
    
    @Override
    public List<BookDTO> getAllBooks(BookDTO bookDTO) {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                   .map(this::convertToDTO)
                   .collect(Collectors.toList());
    }

    @Override
    public Optional<BookDTO> getBookById(int id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(this::convertToDTO);
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        Book book = convertToEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return convertToDTO(savedBook);
    }

    @Override
    public void deleteBookById(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO) {
        Book book = convertToEntity(bookDTO);
        
        // Handle author relationships if provided
        if (bookDTO.getAuthorNames() != null && !bookDTO.getAuthorNames().isEmpty()) {
            List<Author> authors = bookDTO.getAuthorNames().stream()
                    .map(authorName -> authorRepository.findByAuthorName(authorName).orElse(null))
                    .filter(author -> author != null)
                    .collect(Collectors.toList());
            book.setAuthors(authors);
        }
        
        Book updatedBook = bookRepository.save(book);
        return convertToDTO(updatedBook);
    }

    private BookDTO convertToDTO(Book book) {
        List<String> authorNames = null;
        if (book.getAuthors() != null) {
            authorNames = book.getAuthors().stream()
                    .map(Author::getAuthor_name)
                    .collect(Collectors.toList());
        }
        
        return BookDTO.builder()
                .id(book.getId())
                .bookName(book.getBookName())
                .publisher(book.getPublisher())
                .authorNames(authorNames)
                .build();
    }

    private Book convertToEntity(BookDTO bookDTO) {
        return Book.builder()
                .id(bookDTO.getId())
                .bookName(bookDTO.getBookName())
                .publisher(bookDTO.getPublisher())
                .build();
    }
}