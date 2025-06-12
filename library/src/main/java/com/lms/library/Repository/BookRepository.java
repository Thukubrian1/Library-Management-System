package com.lms.library.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lms.library.Model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    
    Optional<Book> findByBookName(String bookName);
    
    Optional<Book> findByPublisher(String publisher);
}