package lk.ijse.controller.userController.book;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import lk.ijse.controller.LoginFormController;
import lk.ijse.dto.TransactionDto;
import lk.ijse.entity.Book;
import lk.ijse.service.custom.TransactionService;
import lk.ijse.service.custom.impl.TransactionServiceImpl;

import java.time.LocalDate;

public class BurrowBookFormController {

    @FXML
    private DatePicker borrowDate;

    @FXML
    private JFXButton confirmBtn;

    @FXML
    private Label lblBookId;

    @FXML
    private Label lblBookTitle;

    @FXML
    private DatePicker returnDate;

    private final TransactionService transactionService;
    private final TransactionDto transactionDto;

    public BurrowBookFormController() {
        this.transactionService = new TransactionServiceImpl();
        this.transactionDto = new TransactionDto();
    }
    public void initialize() {
        borrowDate.setValue(LocalDate.now());
        LocalDate today = LocalDate.now();
        borrowDate.setEditable(false);
        LocalDate returnDate1 = today.plusDays(14);
        returnDate.setValue(returnDate1);
        returnDate.setEditable(false);
    }
    @FXML
    void confirmBtnOnAction(ActionEvent event) {
        String bookIdText = lblBookId.getText();
        // Extracting the numeric part from the string
        String numericPart = bookIdText.replaceAll("[^\\d]", "");
        // Parsing the extracted numeric part into a long
        long bookId = Long.parseLong(numericPart);
        transactionDto.setUserid(LoginFormController.user.getId());
        transactionDto.setId(generateNextId());
        transactionDto.setBookID(bookId);
        transactionDto.setDueDate(borrowDate.getValue());
        transactionDto.setReturnDate(returnDate.getValue());
        transactionDto.setStatus("borrow");

        transactionService.save(transactionDto);
    }
    public Label getLblBookId() {
        return lblBookId;
    }

    public Label getLblBookTitle(){
        return lblBookTitle;
    }
    private String generateNextId() {
        String transactionId = transactionService.generateNewTransactionId();
        return transactionId;
    }

}
