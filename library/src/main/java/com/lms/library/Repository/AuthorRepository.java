package com.lms.library.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.lms.library.Model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    
    @Query("SELECT a FROM Author a WHERE a.author_name = :authorName")
    Optional<Author> findByAuthorName(@Param("authorName") String authorName);
    
    Optional<Author> findByEmail(String email);
}