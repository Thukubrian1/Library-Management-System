package com.lms.library.Controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lms.library.Service.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.lms.library.DTO.BookDTO;




@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:4200")
public class BookController{

    @Autowired
    private BookService bookService;
    
    @GetMapping
    
    public ResponseEntity<List<BookDTO>> getAllBooks(){

        List<BookDTO> books = bookService.getAllBooks(null);

        return ResponseEntity.ok(books);

        }

    @GetMapping("/{id}")

    public ResponseEntity<BookDTO> getBookById(@PathVariable int id){

        Optional<BookDTO> book = bookService.getBookById(id);

        return book.map(bookDTO -> ResponseEntity.ok(bookDTO))
        .orElse(ResponseEntity.notFound().build());
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable int id){

        Optional<BookDTO> book = bookService.getBookById(id);

        if(book.isPresent()){

            bookService.deleteBookById(id);

            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO){

        BookDTO savedBook = bookService.addBook(bookDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    
    }


    @PutMapping("/{id}")

    public ResponseEntity<BookDTO> updateBook(@PathVariable int id, @RequestBody BookDTO bookDTO){

        Optional<BookDTO> book = bookService.getBookById(id);

        if(book.isPresent()){

            bookDTO.setId(id);
            // bookDTO.setBookName(bookDTO);

            BookDTO updatedBook = bookService.updateBook(bookDTO);

            return ResponseEntity.ok(updatedBook);
        }

        return ResponseEntity.notFound().build();
        
    }
}