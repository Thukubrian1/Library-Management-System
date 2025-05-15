package com.lms.library.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.lms.library.Model.Author;

@Service
public interface  AuthorService {

    public List<Author> getAuthors(Author author);

    public List<Author> getAuthorById(int id);

    public Author addAuthor(Author author);

    public Author updateAuthor(int id);

    public void deleteAuthor(int id);
}
