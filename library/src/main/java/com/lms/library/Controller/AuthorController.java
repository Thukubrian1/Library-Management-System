package com.lms.library.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lms.library.Model.Author;
import com.lms.library.Service.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<Object> getAuthors(Author authors){
        return new ResponseEntity<>(authorService.getAuthors(authors),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAuthorById(@PathVariable int id){
        return new ResponseEntity<>(authorService.getAuthorById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addAuthor(@RequestBody Author author){
        return new ResponseEntity<>(authorService.addAuthor(author),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAuthor(@PathVariable int id){
        return new ResponseEntity<>(authorService.updateAuthor(id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable int id){
        authorService.deleteAuthor(id);
    }
}
