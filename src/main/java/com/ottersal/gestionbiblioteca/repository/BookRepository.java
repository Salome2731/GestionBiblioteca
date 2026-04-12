package com.ottersal.gestionbiblioteca.repository;

import com.ottersal.gestionbiblioteca.model.Book;
import com.ottersal.gestionbiblioteca.utilities.Conexion;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class BookRepository {
private final BookRepositoryHelper helper;

    public BookRepository(BookRepositoryHelper helper) {
        this.helper = helper;
    }

    // LISTAR LIBROS
    public List<Book> getBooks() {
        List<Book> result = new ArrayList<>();
        Conexion conexion = new Conexion();
        try (Connection conn = conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(helper.listBooks());
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Book b = new Book();
                b.setId(UUID.fromString(rs.getString(1)));
                b.setTitle(rs.getString(2));
                b.setAuthor(rs.getString(3));
                b.setType(rs.getString(4));
                b.setEditorial(rs.getString(5));
                b.setTotalQuantity(rs.getInt(6));
                b.setAvailableQuantity(rs.getInt(7));
                b.setStatus(rs.getString(8));
                result.add(b);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return result;
    }

    // GUARDAR LIBRO (POST)
    public Book saveBook(Book book) {
        if (book.getId() == null) book.setId(UUID.randomUUID());
        Conexion conexion = new Conexion();
        try (Connection conn = conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(helper.insertBook())) {
            ps.setString(1, book.getId().toString());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setString(4, book.getType());
            ps.setString(5, book.getEditorial());
            ps.setInt(6, book.getTotalQuantity());
            ps.setInt(7, book.getAvailableQuantity());
            ps.setString(8, book.getStatus());
            ps.executeUpdate();
            return book;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // ELIMINAR LIBRO (DELETE)
    public boolean deleteBook(UUID id) {
        Conexion conexion = new Conexion();
        try (Connection conn = conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(helper.deleteBook())) {
            ps.setString(1, id.toString());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
