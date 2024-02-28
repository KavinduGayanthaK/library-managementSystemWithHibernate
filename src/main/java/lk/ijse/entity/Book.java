package lk.ijse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "book")
public class Book {
    @Id
    private long id;
    private String title;
    private String genre;
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    private Author author;
}
