package lk.ijse.tm;

public class TransactionTm {
    private long bookId;
    private String bookTitle;
    private String genre;
    private String borrowedDate;
    private String returnDate;
    private String status;
    private String userId;

    public TransactionTm() {
    }

    public TransactionTm(long bookId, String bookTitle, String genre, String borrowedDate, String returnDate, String status, String userId) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.genre = genre;
        this.borrowedDate = borrowedDate;
        this.returnDate = returnDate;
        this.status = status;
        this.userId = userId;
    }

    public TransactionTm(long bookId, String bookTitle, String genre, String borrowedDate, String returnDate, String status) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.genre = genre;
        this.borrowedDate = borrowedDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(String borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "TransactionTm{" +
                "bookId=" + bookId +
                ", bookTitle='" + bookTitle + '\'' +
                ", genre='" + genre + '\'' +
                ", borrowedDate='" + borrowedDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", status='" + status + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
