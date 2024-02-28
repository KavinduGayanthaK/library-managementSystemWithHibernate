package lk.ijse.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,length = 40)
    private String firstname;
    @Column(nullable = false,length = 40)
    private String secondName;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> bookList;
}
