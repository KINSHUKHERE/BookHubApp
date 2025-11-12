// Kinshuk khandelwal (13565)
package com.bookhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.bookhub.model.Book;
import com.bookhub.repository.BookRepository;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookRepository repo;

    // 🏠 Homepage
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // 📚 View all books
    @GetMapping("/books")
    public String viewBooks(Model model) {
        List<Book> books = repo.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    // ➕ Show add form
    @GetMapping("/addbook")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    // 💾 Save new or updated book
    @PostMapping("/savebook")
    public String saveBook(@ModelAttribute Book book) {
        repo.save(book);
        return "redirect:/books";
    }

    // ✏️ Edit book (by ID)
    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable int id, Model model) {
        Book book = repo.findById(id).orElse(null);
        model.addAttribute("book", book);
        return "addbook"; // reuse same form for editing
    }

    // ❌ Delete book (by ID)
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        repo.deleteById(id);
        return "redirect:/books";
    }
}
