package lk.ijse.controller.adminController.books;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

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

    @FXML
    void confirmBtnOnAction(ActionEvent event) {

    }

    public void initialize(){
        borrowDate.setValue(LocalDate.now());
        LocalDate today = LocalDate.now();
        borrowDate.setEditable(false);
        LocalDate returnDate1 = today.plusDays(14);
        returnDate.setValue(returnDate1);
        returnDate.setEditable(false);

    }

    public Label getLblBookId() {
        return lblBookId;
    }

    public Label getLblBookTitle(){
        return lblBookTitle;
    }
}
