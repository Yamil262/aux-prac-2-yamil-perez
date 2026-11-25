package com.biblioteca.libros.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Datos de entrada para crear o actualizar un libro (sin id: lo genera el servidor).
 */
@Schema(description = "Datos para registrar o actualizar un libro")
public class BookRequest {

    @Schema(example = "Cien años de soledad")
    @NotBlank(message = "El título es obligatorio")
    private String title;

    @Schema(example = "Gabriel García Márquez")
    @NotBlank(message = "El autor es obligatorio")
    private String author;

    @Schema(example = "978-0307474728")
    @NotBlank(message = "El ISBN es obligatorio")
    private String isbn;

    @Schema(example = "1967")
    @NotNull(message = "El año de publicación es obligatorio")
    @Min(value = 1000, message = "El año de publicación debe ser mayor o igual a 1000")
    @Max(value = 2100, message = "El año de publicación debe ser menor o igual a 2100")
    private Integer publicationYear;

    @Schema(example = "Novela")
    @NotBlank(message = "La categoría es obligatoria")
    private String category;

    @Schema(example = "true")
    @NotNull(message = "El campo available es obligatorio")
    private Boolean available;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public Integer getPublicationYear() { return publicationYear; }
    public void setPublicationYear(Integer publicationYear) { this.publicationYear = publicationYear; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Boolean getAvailable() { return available; }
    public void setAvailable(Boolean available) { this.available = available; }
}
