package lk.ijse.dto;

import lk.ijse.entity.Book;
import lk.ijse.entity.User;

import java.time.LocalDate;

public class TransactionDto {
    private String id;
    private String userid;
    private long bookID;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String status;

    public TransactionDto() {
    }

    public TransactionDto(String id, String userid, long bookID, LocalDate dueDate, LocalDate returnDate, String status) {
        this.id = id;
        this.userid = userid;
        this.bookID = bookID;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public long getBookID() {
        return bookID;
    }

    public void setBookID(long bookID) {
        this.bookID = bookID;
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
        return "TransactionDto{" +
                "id='" + id + '\'' +
                ", userid='" + userid + '\'' +
                ", bookID=" + bookID +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                ", status='" + status + '\'' +
                '}';
    }
}
