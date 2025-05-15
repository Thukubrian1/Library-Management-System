package com.lms.library.Repository;

import com.lms.library.Model.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> getBookById(int id);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b = b WHERE b.id = :id")
    void updateBook(@Param("id") int id);
}