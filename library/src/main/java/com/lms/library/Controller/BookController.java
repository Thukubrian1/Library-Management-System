package com.lms.library.Controller;

import com.lms.library.Model.Book;
import com.lms.library.Service.BookService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<Object> getAllBook() {
        return new ResponseEntity<>(bookService.getAllBooks(),HttpStatus.OK);
    }

   @GetMapping("/{id}")
   public ResponseEntity<Object> getBookById(@PathVariable int id){
       return new ResponseEntity<>(bookService.getBookById(id),HttpStatus.OK);
   }

   @PostMapping
   public ResponseEntity<Object> addBook(@RequestBody Book book) {
    
       return new ResponseEntity<>(bookService.addBook(book),HttpStatus.CREATED);
   }

   @PutMapping("/{id}")
   public ResponseEntity<Object> updateBook(@PathVariable int id, @RequestBody Book book) {
       bookService.updateBook(id);
       
       return new ResponseEntity<>("Book Updated Successfully",HttpStatus.OK);
   }
   
   @DeleteMapping("/{id}")
   public ResponseEntity<Object> deleteBook(@PathVariable int id, @RequestBody Book book) {
       bookService.deleteBook(id);
       return new ResponseEntity<>("Book Deleted Successfully", HttpStatus.OK);
   }
}