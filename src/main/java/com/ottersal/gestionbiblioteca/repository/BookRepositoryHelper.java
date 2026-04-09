package com.ottersal.gestionbiblioteca.repository;

public class BookRepositoryHelper {
public String listBooks() {
        return "SELECT id, title, author, type, editorial, total_quantity, available_quantity, status FROM book";
    }

    public String insertBook() {
        return "INSERT INTO book (id, title, author, type, editorial, total_quantity, available_quantity, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    }

    public String updateBook() {
        return "UPDATE book SET title=?, author=?, type=?, editorial=?, total_quantity=?, available_quantity=?, status=? WHERE id=?";
    }

    public String deleteBook() {
        return "DELETE FROM book WHERE id = ?";
    }
}
