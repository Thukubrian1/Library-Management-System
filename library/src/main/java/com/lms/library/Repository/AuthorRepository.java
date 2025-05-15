package com.lms.library.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.lms.library.Model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    
    List<Author> findById(int id);

    @Modifying
    @Transactional
    @Query("UPDATE Author a SET a = a WHERE a.id = :id")
    void updateAuthor(@Param("id") int id);
    
    // Alternative implementation if you need to return the updated Author
    @Query("SELECT a FROM Author a WHERE a.id = :id")
    Author findAuthorById(@Param("id") int id);
}