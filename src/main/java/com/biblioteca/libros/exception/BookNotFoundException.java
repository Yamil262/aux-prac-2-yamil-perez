package com.biblioteca.libros.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Long id) {
        super("No se encontró un libro con id " + id);
    }
}
