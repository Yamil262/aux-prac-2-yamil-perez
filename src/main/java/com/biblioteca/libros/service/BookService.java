package com.biblioteca.libros.service;

import com.biblioteca.libros.dto.BookRequest;
import com.biblioteca.libros.dto.BookResponse;
import com.biblioteca.libros.exception.BookNotFoundException;
import com.biblioteca.libros.model.Book;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;
@Service
public class BookService {

    private final List<Book> books = new CopyOnWriteArrayList<>();
    private final AtomicLong sequence = new AtomicLong(1);

    public BookResponse create(BookRequest request) {
        Book book = new Book();
        book.setId(sequence.getAndIncrement());
        apply(book, request);
        books.add(book);
        return toResponse(book);
    }

    public List<BookResponse> findAll() {
        return books.stream().map(this::toResponse).toList();
    }

    public BookResponse findById(Long id) {
        return toResponse(getOrThrow(id));
    }

    public BookResponse update(Long id, BookRequest request) {
        Book book = getOrThrow(id);
        apply(book, request);
        return toResponse(book);
    }

    public void delete(Long id) {
        Book book = getOrThrow(id);
        books.remove(book);
    }

    // ---------- helpers ----------

    private Book getOrThrow(Long id) {
        return books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    private void apply(Book book, BookRequest r) {
        book.setTitle(r.getTitle());
        book.setAuthor(r.getAuthor());
        book.setIsbn(r.getIsbn());
        book.setPublicationYear(r.getPublicationYear());
        book.setCategory(r.getCategory());
        book.setAvailable(r.getAvailable());
    }

    private BookResponse toResponse(Book b) {
        return new BookResponse(b.getId(), b.getTitle(), b.getAuthor(), b.getIsbn(),
                b.getPublicationYear(), b.getCategory(), b.isAvailable());
    }
}
