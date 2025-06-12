package com.lms.library.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.lms.library.DTO.AuthorDTO;
import com.lms.library.Model.Author;
import com.lms.library.Model.Book;
import com.lms.library.Repository.AuthorRepository;
import com.lms.library.Repository.BookRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {
    

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    @Override
    public List<AuthorDTO> getAuthors(AuthorDTO authorDTO) {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                      .map(this::convertToDTO)
                      .collect(Collectors.toList());
    }

    @Override
    public Optional<AuthorDTO> getAuthorById(int id) {
        Optional<Author> author = authorRepository.findById(id);
        return author.map(this::convertToDTO);
    }

    @Override
    public AuthorDTO addAuthor(AuthorDTO authorDTO) {
        Author author = convertToEntity(authorDTO);
        Author savedAuthor = authorRepository.save(author);
        return convertToDTO(savedAuthor);
    }

    @Override
    public AuthorDTO updateAuthor(AuthorDTO authorDTO) {
        Author author = convertToEntity(authorDTO);
        
        // Handle book relationships if provided
        if (authorDTO.getBookNames() != null && !authorDTO.getBookNames().isEmpty()) {
            List<Book> books = authorDTO.getBookNames().stream()
                    .map(bookName -> bookRepository.findByBookName(bookName).orElse(null))
                    .filter(book -> book != null)
                    .collect(Collectors.toList());
            author.setBooks(books);
        }
        
        Author updatedAuthor = authorRepository.save(author);
        return convertToDTO(updatedAuthor);
    }

    @Override
    public void deleteAuthor(int id) {
        authorRepository.deleteById(id);
    }

    private AuthorDTO convertToDTO(Author author) {
        List<String> bookNames = null;
        if (author.getBooks() != null) {
            bookNames = author.getBooks().stream()
                    .map(Book::getBookName)
                    .collect(Collectors.toList());
        }
        
        return AuthorDTO.builder()
                .id(author.getId())
                .author_name(author.getAuthor_name())
                .email(author.getEmail())
                .bookNames(bookNames)
                .build();
    }

    private Author convertToEntity(AuthorDTO authorDTO) {
        return Author.builder()
                .id(authorDTO.getId())
                .author_name(authorDTO.getAuthor_name())
                .email(authorDTO.getEmail())
                .build();
    }
}