package cl.praxis.GestorBiblioteca.model;

public class Book {
    private String id;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book() {
    }

    public Book(String id, String title, String author, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = isAvailable;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getDetails() {
        return "ID: " + id + ", Título: " + title + ", Autor: " + author + ", Disponible: " + isAvailable;
    }
}
