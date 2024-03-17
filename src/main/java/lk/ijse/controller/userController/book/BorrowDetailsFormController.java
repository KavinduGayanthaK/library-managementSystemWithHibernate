package lk.ijse.controller.userController.book;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import lk.ijse.service.custom.TransactionService;
import lk.ijse.service.custom.impl.TransactionServiceImpl;
import lk.ijse.tm.TransactionTm;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BorrowDetailsFormController {

    TransactionService transactionService = new TransactionServiceImpl();

    @FXML
    private TableView<TransactionTm> borrowDetailsTable;

    public void loadALlTransaction() {
        List<TransactionTm> transactionTms = transactionService.getAll().stream().map(tra -> new TransactionTm(
                tra.getBookId(),
                tra.getBookTitle(),
                tra.getGenre(),
                tra.getBorrowedDate(),
                tra.getReturnDate(),
                tra.getStatus()
        )).collect(Collectors.toList());
        List<TransactionTm> filteredList = transactionTms.stream()
                .filter(transaction -> transaction.getBookId() != 0)
                .collect(Collectors.toList());

        ObservableList<TransactionTm> observableList = FXCollections.observableArrayList(filteredList);
        borrowDetailsTable.setItems(observableList);
    }

    public void initialize() {
        borrowDetailsTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("bookId"));
        borrowDetailsTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        borrowDetailsTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("genre"));
        borrowDetailsTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("borrowedDate"));
        borrowDetailsTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        borrowDetailsTable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("status"));
        loadALlTransaction();
        Platform.runLater(() -> {
            returnBtnTable();
        });
    }

    private void returnBtnTable() {
        TableColumn<TransactionTm, Void> colBtn = new TableColumn<>("Action");
        Callback<TableColumn<TransactionTm, Void>, TableCell<TransactionTm, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<TransactionTm, Void> call(final TableColumn<TransactionTm, Void> param) {
                final TableCell<TransactionTm, Void> cell = new TableCell<>() {
                    private final JFXButton btn = new JFXButton("Return");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            TransactionTm data = getTableView().getItems().get(getIndex());
                            System.out.println("Selected Data: " + data);
                            boolean isReturn = returnBtnOnAction(data, btn);
                            if (isReturn) {
                                new Alert(Alert.AlertType.INFORMATION, "Returned").show();
                            }
                        });
                    }

                    private boolean returnBtnOnAction(TransactionTm data, JFXButton btn) {
                        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);

                        Optional<ButtonType> type = new Alert(
                                Alert.AlertType.CONFIRMATION, "Do you want to return this book?", yes, no).showAndWait();
                        if (type.orElse(no) == yes) {
                            try {
                                boolean isReturnBook = transactionService.update(data.getBookId());
                                if (isReturnBook) {
                                    return true;
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        return false;
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            TransactionTm data = getTableView().getItems().get(getIndex());
                            if ("borrow".equals(data.getStatus())) {
                                btn.setCursor(Cursor.HAND);
                                btn.setStyle("-fx-background-color: #cc0808");
                                setGraphic(btn);
                            } else {
                                setGraphic(null);
                            }
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        borrowDetailsTable.getColumns().add(colBtn);
    }
}
