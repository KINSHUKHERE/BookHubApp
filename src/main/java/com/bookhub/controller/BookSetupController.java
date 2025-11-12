package com.bookhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
public class BookSetupController {

    @Autowired
    private JdbcTemplate jdbc;

    // 1️⃣ Create table
    @GetMapping("/init")
    public String initTable() {
        jdbc.execute("CREATE TABLE IF NOT EXISTS book (id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(100), author VARCHAR(100), price DOUBLE)");
        return "Book table created successfully!";
    }

    // 2️⃣ Add book
    @GetMapping("/add/{title}/{author}/{price}")
    public String addBook(@PathVariable String title, @PathVariable String author, @PathVariable double price) {
        jdbc.update("INSERT INTO book (title, author, price) VALUES (?, ?, ?)", title, author, price);
        return "Book added: " + title;
    }

    // 3️⃣ View all books
    @GetMapping("/booksapi")
    public List<Map<String, Object>> getBooks() {
        return jdbc.queryForList("SELECT * FROM book");
    }
}
