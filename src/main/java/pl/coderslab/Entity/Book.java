package pl.coderslab.Entity;

public class Book {
    private Integer id;
    private String title, isbn;
    private Author author;

    public Book() {}

    public Book(Author author, String title, String isbn) {
        setAuthor(author);
        setTitle(title);
        setIsbn(isbn);
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
