package br.uff.ic.devappcorp.entities;

import br.uff.ic.devappcorp.entities.Author;
import br.uff.ic.devappcorp.entities.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDto {
    private Long id;
    private String title;
    private String authorName;
    private String genre;
    private String isbn;

    public BookDto() {}

    public Book toBook(){
        List authors = new ArrayList<Author>();
        String firstName = authorName.split(" ")[0];
        String lastName = authorName.split(" ")[1];
        Author author = new Author(firstName, lastName);
        authors.add(author);
        return new Book(isbn, title, authors, genre);
    }

    public static BookDto fromBook(Book book){
        BookDto dto = new BookDto();
        dto.setId(book.getId());
        dto.setAuthorName(book.getAuthors().get(0).getFullName());
        dto.setGenre(book.getGenre());
        dto.setTitle(book.getTitle());
        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


}
