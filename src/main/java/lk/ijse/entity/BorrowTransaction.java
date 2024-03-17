package lk.ijse.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "transaction")
public class BorrowTransaction {
    @Id
    private String id;

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bookId")
    private Book book;

    private LocalDate dueDate;
    private LocalDate returnDate;
    private String status;

    public BorrowTransaction() {
    }

    public BorrowTransaction(String id, User user, Book book, LocalDate dueDate, LocalDate returnDate, String status) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", book=" + book +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                ", status='" + status + '\'' +
                '}';
    }
}
