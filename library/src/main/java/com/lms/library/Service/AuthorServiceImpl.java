package com.lms.library.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lms.library.Model.Author;
import com.lms.library.Repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {
    
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> getAuthors(Author author) {
        if (author != null){
            return authorRepository.findAll();
        }
        else{
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    @Override
    public List<Author> getAuthorById(int id) {
        return authorRepository.findById(id);
    }

    @Override
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(int id) {
        // Call the void updateAuthor method
        authorRepository.updateAuthor(id);
        // Then fetch and return the updated author
        return authorRepository.findAuthorById(id);
    }

    @Override
    public void deleteAuthor(int id) {
        authorRepository.deleteById(id);
    }
}