package com.lms.library.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.lms.library.DTO.AuthorDTO;


@Service
public interface  AuthorService {

    public List<AuthorDTO> getAuthors(AuthorDTO authorDTO);

    public Optional<AuthorDTO> getAuthorById(int id);

    public AuthorDTO addAuthor(AuthorDTO authorDTO);

    public AuthorDTO updateAuthor(AuthorDTO authorDTO);

    public void deleteAuthor(int id);
}
