package lk.ijse.tm;

public class AdminTransactionTm {
    private String userID;
    private String userName;
    private long bookId;
    private String bookTitle;
    private String genre;
    private String borrowedDate;
    private String returnDate;
    private String status;

    public AdminTransactionTm(String userID, String userName, long bookId, String bookTitle, String genre, String borrowedDate, String returnDate, String status) {
        this.userID = userID;
        this.userName = userName;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.genre = genre;
        this.borrowedDate = borrowedDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public AdminTransactionTm() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    @Override
    public String toString() {
        return "AdminTransactionTm{" +
                "userID='" + userID + '\'' +
                ", userName='" + userName + '\'' +
                ", bookId=" + bookId +
                ", bookTitle='" + bookTitle + '\'' +
                ", genre='" + genre + '\'' +
                ", borrowedDate='" + borrowedDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
