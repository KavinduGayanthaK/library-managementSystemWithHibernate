package lk.ijse.entity;


import com.sun.prism.Image;
import jakarta.persistence.*;


@Entity(name = "book")
public class Book {
    @Id
    private long id;
    private String title;
    private String genre;
    private String image;
    private String status;
    private String author;


    public Book() {
    }

    public Book(long id, String title, String genre, String image, String status, String author) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.image = image;
        this.status = status;
        this.author = author;
    }

    public Book(long id, String title, String genre, String image, String status) {

    }

    public Book(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", image='" + image + '\'' +
                ", status='" + status + '\'' +
                ", author=" + author +
                '}';
    }
}
