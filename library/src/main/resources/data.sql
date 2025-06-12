-- ============================================
-- Library Management System Database Setup
-- ============================================

-- Drop tables if they exist (for clean setup)
DROP TABLE IF EXISTS author_books;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS authors;

-- ============================================
-- Create Authors Table
-- ============================================
CREATE TABLE authors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    author_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- ============================================
-- Create Books Table
-- ============================================
CREATE TABLE book (
    id INT AUTO_INCREMENT PRIMARY KEY,
    book_name VARCHAR(500) NOT NULL,
    publisher VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- ============================================
-- Create Junction Table for Many-to-Many Relationship
-- ============================================
CREATE TABLE author_books (
    author_id INT NOT NULL,
    book_id INT NOT NULL,
    PRIMARY KEY (author_id, book_id),
    FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES book(id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================================
-- Insert Sample Authors
-- ============================================
INSERT INTO authors (author_name, email) VALUES
('J.K. Rowling', 'jk.rowling@email.com'),
('George R.R. Martin', 'george.martin@email.com'),
('Stephen King', 'stephen.king@email.com'),
('Agatha Christie', 'agatha.christie@email.com'),
('J.R.R. Tolkien', 'tolkien@email.com'),
('Dan Brown', 'dan.brown@email.com'),
('Harper Lee', 'harper.lee@email.com'),
('Jane Austen', 'jane.austen@email.com'),
('Mark Twain', 'mark.twain@email.com'),
('Ernest Hemingway', 'hemingway@email.com');

-- ============================================
-- Insert Sample Books
-- ============================================
INSERT INTO book (book_name, publisher) VALUES
('Harry Potter and the Philosopher''s Stone', 'Bloomsbury Publishing'),
('Harry Potter and the Chamber of Secrets', 'Bloomsbury Publishing'),
('Harry Potter and the Prisoner of Azkaban', 'Bloomsbury Publishing'),
('A Game of Thrones', 'Bantam Spectra'),
('A Clash of Kings', 'Bantam Spectra'),
('A Storm of Swords', 'Bantam Spectra'),
('The Shining', 'Doubleday'),
('It', 'Viking Press'),
('The Stand', 'Doubleday'),
('Murder on the Orient Express', 'Collins Crime Club'),
('And Then There Were None', 'Collins Crime Club'),
('The ABC Murders', 'Collins Crime Club'),
('The Hobbit', 'George Allen & Unwin'),
('The Fellowship of the Ring', 'George Allen & Unwin'),
('The Two Towers', 'George Allen & Unwin'),
('The Return of the King', 'George Allen & Unwin'),
('The Da Vinci Code', 'Doubleday'),
('Angels & Demons', 'Pocket Books'),
('The Lost Symbol', 'Doubleday'),
('To Kill a Mockingbird', 'J. B. Lippincott & Co.'),
('Pride and Prejudice', 'T. Egerton'),
('Emma', 'John Murray'),
('Sense and Sensibility', 'Thomas Egerton'),
('The Adventures of Tom Sawyer', 'American Publishing Company'),
('Adventures of Huckleberry Finn', 'Charles L. Webster And Company'),
('The Old Man and the Sea', 'Charles Scribner''s Sons'),
('A Farewell to Arms', 'Charles Scribner''s Sons'),
('For Whom the Bell Tolls', 'Charles Scribner''s Sons');

-- ============================================
-- Create Author-Book Relationships
-- ============================================

-- J.K. Rowling's books (Harry Potter series)
INSERT INTO author_books (author_id, book_id) VALUES
(1, 1), (1, 2), (1, 3);

-- George R.R. Martin's books (A Song of Ice and Fire series)
INSERT INTO author_books (author_id, book_id) VALUES
(2, 4), (2, 5), (2, 6);

-- Stephen King's books
INSERT INTO author_books (author_id, book_id) VALUES
(3, 7), (3, 8), (3, 9);

-- Agatha Christie's books
INSERT INTO author_books (author_id, book_id) VALUES
(4, 10), (4, 11), (4, 12);

-- J.R.R. Tolkien's books (Lord of the Rings series)
INSERT INTO author_books (author_id, book_id) VALUES
(5, 13), (5, 14), (5, 15), (5, 16);

-- Dan Brown's books
INSERT INTO author_books (author_id, book_id) VALUES
(6, 17), (6, 18), (6, 19);

-- Harper Lee's book
INSERT INTO author_books (author_id, book_id) VALUES
(7, 20);

-- Jane Austen's books
INSERT INTO author_books (author_id, book_id) VALUES
(8, 21), (8, 22), (8, 23);

-- Mark Twain's books
INSERT INTO author_books (author_id, book_id) VALUES
(9, 24), (9, 25);

-- Ernest Hemingway's books
INSERT INTO author_books (author_id, book_id) VALUES
(10, 26), (10, 27), (10, 28);

-- ============================================
-- Example of books with multiple authors (collaborative works)
-- ============================================

-- Let's say "The Stand" was also co-authored by another author (hypothetical)
-- INSERT INTO author_books (author_id, book_id) VALUES (3, 9), (2, 9);

-- ============================================
-- Useful Queries for Testing
-- ============================================

-- Query 1: Get all authors with their books
SELECT 
    a.id as author_id,
    a.author_name,
    a.email,
    b.id as book_id,
    b.book_name,
    b.publisher
FROM authors a
LEFT JOIN author_books ab ON a.id = ab.author_id
LEFT JOIN book b ON ab.book_id = b.id
ORDER BY a.author_name, b.book_name;

-- Query 2: Get all books with their authors
SELECT 
    b.id as book_id,
    b.book_name,
    b.publisher,
    a.id as author_id,
    a.author_name,
    a.email
FROM book b
LEFT JOIN author_books ab ON b.id = ab.book_id
LEFT JOIN authors a ON ab.author_id = a.id
ORDER BY b.book_name, a.author_name;

-- Query 3: Get authors who have written more than 2 books
SELECT 
    a.author_name,
    COUNT(ab.book_id) as book_count
FROM authors a
JOIN author_books ab ON a.id = ab.author_id
GROUP BY a.id, a.author_name
HAVING COUNT(ab.book_id) > 2
ORDER BY book_count DESC;

-- Query 4: Get books that have multiple authors (if any)
SELECT 
    b.book_name,
    COUNT(ab.author_id) as author_count
FROM book b
JOIN author_books ab ON b.id = ab.book_id
GROUP BY b.id, b.book_name
HAVING COUNT(ab.author_id) > 1;

-- Query 5: Find specific author's books
SELECT 
    b.book_name,
    b.publisher
FROM book b
JOIN author_books ab ON b.id = ab.book_id
JOIN authors a ON ab.author_id = a.id
WHERE a.author_name = 'J.K. Rowling';

-- Query 6: Find authors of a specific book
SELECT 
    a.author_name,
    a.email
FROM authors a
JOIN author_books ab ON a.id = ab.author_id
JOIN book b ON ab.book_id = b.id
WHERE b.book_name = 'The Hobbit';

-- Query 7: Count total books and authors
SELECT 
    (SELECT COUNT(*) FROM authors) as total_authors,
    (SELECT COUNT(*) FROM book) as total_books,
    (SELECT COUNT(*) FROM author_books) as total_relationships;

