package lk.ijse.controller.adminController.books;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.service.custom.TransactionService;
import lk.ijse.service.custom.impl.TransactionServiceImpl;
import lk.ijse.tm.AdminTransactionTm;
import lk.ijse.tm.TransactionTm;

import java.util.List;
import java.util.stream.Collectors;

public class BorrowDetailsFormController {

    @FXML
    private TableView<AdminTransactionTm> borrowDetailsTable;

    @FXML
    void txtSearch(KeyEvent event) {

    }
    TransactionService transactionService = new TransactionServiceImpl();
    public void initialize(){
        borrowDetailsTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("userID"));
        borrowDetailsTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("userName"));
        borrowDetailsTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("bookId"));
        borrowDetailsTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        borrowDetailsTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("genre"));
        borrowDetailsTable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("borrowedDate"));
        borrowDetailsTable.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        borrowDetailsTable.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("status"));
        loadAll();
    }


    public void loadAll(){
        List<AdminTransactionTm> transactionTms = transactionService.getAll1().stream().map(tra -> new AdminTransactionTm(
                tra.getUserID(),
                tra.getUserName(),
                tra.getBookId(),
                tra.getBookTitle(),
                tra.getGenre(),
                tra.getBorrowedDate(),
                tra.getReturnDate(),
                tra.getStatus()
        )).collect(Collectors.toList());

        ObservableList<AdminTransactionTm> observableList = FXCollections.observableArrayList(transactionTms);
        borrowDetailsTable.setItems(observableList);
    }
}
