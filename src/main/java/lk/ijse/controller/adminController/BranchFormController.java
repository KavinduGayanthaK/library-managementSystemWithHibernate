package lk.ijse.controller.adminController;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import lk.ijse.service.custom.BranchService;
import lk.ijse.service.custom.impl.BranchServiceImpl;
import lk.ijse.tm.BranchTm;
import lk.ijse.tm.TransactionTm;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BranchFormController {

    @FXML
    private TableView<BranchTm> tblBranch;
    public static TableView<BranchTm> tmTableView;

    BranchService branchService = new BranchServiceImpl();
    public static BranchTm branchTm = new BranchTm();
    @FXML
    void addBranchBtn(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/admin/addBranch_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Add Branch");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

    public void initialize(){
        tblBranch.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("branchId"));
        tblBranch.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblBranch.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("city"));
        tblBranch.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblBranch.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("postelCode"));
        loadAll();
        getTable();
    }

    private void loadAll() {
        List<BranchTm> branchTms = branchService.getAll().stream()
                .map(branch -> new BranchTm(
                        branch.getBranchId(),
                        branch.getName(),
                        branch.getCity(),
                        branch.getAddress(),
                        branch.getPostelCode()
                ))
                .collect(Collectors.toList());

        ObservableList<BranchTm> observableList = FXCollections.observableArrayList(branchTms);
        tblBranch.setItems(observableList);
        updateButtonToTable();
        deleteButtonToTable();
    }

    private void updateButtonToTable() {
        TableColumn<BranchTm, Void> colBtn = new TableColumn("Action");
        Callback<TableColumn<BranchTm, Void>, TableCell<BranchTm, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<BranchTm, Void> call(final TableColumn<BranchTm, Void> param) {
                final TableCell<BranchTm, Void> cell = new TableCell<BranchTm, Void>() {
                    private final JFXButton btn = new JFXButton("Update");
                    {
                        btn.setOnAction((ActionEvent event) -> {

                            BranchTm data = getTableView().getItems().get(getIndex());
                            branchTm.setBranchId(data.getBranchId());
                            branchTm.setName(data.getName());
                            branchTm.setCity(data.getCity());
                            branchTm.setAddress(data.getAddress());
                            branchTm.setPostelCode(data.getPostelCode());

                            boolean isUpdated = updateBtnOnAction(data,btn);


                        });
                    }
                    private boolean updateBtnOnAction(BranchTm data, Button btn) {

                        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);

                        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION,
                                "Are you want to change details ?", yes, no).showAndWait();
                        if (type.orElse(no) == yes) {

                            URL resource = this.getClass().getResource("/view/admin/updateBranch_form.fxml");
                            FXMLLoader fxmlLoader = new FXMLLoader(resource);
                            Parent load = null;
                            try {
                                load = fxmlLoader.load();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            Stage stage = new Stage();
                            stage.setTitle("Vehicle Form");
                            stage.setScene(new Scene(load));
                            stage.centerOnScreen();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.setResizable(false);
                            stage.show();

                        }
                        return false;
                    }


                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {

                            setGraphic(null);
                        } else {
                            btn.setCursor(Cursor.HAND);
                            btn.setStyle("-fx-background-color: #42c923");
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        tblBranch.getColumns().add(colBtn);
    }

    private void deleteButtonToTable() {
        TableColumn<BranchTm, Void> colBtn = new TableColumn("Action");
        Callback<TableColumn<BranchTm, Void>, TableCell<BranchTm, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<BranchTm, Void> call(final TableColumn<BranchTm, Void> param) {
                final TableCell<BranchTm, Void> cell = new TableCell<BranchTm, Void>() {
                    private final JFXButton btn = new JFXButton("Delete");
                    {
                        btn.setOnAction((ActionEvent event) -> {

                            BranchTm data = getTableView().getItems().get(getIndex());
                            branchTm.setBranchId(data.getBranchId());
                            branchTm.setName(data.getName());
                            branchTm.setCity(data.getCity());
                            branchTm.setAddress(data.getAddress());
                            branchTm.setPostelCode(data.getPostelCode());

                            boolean isDelete = deleteBtnOnAction(data,btn);
                            if (isDelete){
                                tblBranch.refresh();
                                new Alert(Alert.AlertType.INFORMATION,"Delete branch").show();
                            }else {
                                new Alert(Alert.AlertType.INFORMATION, "Can't delete branch").show();
                            }
                        });
                    }
                    private boolean deleteBtnOnAction(BranchTm data, Button btn) {

                        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);

                        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION,
                                "Are you want to change details ?", yes, no).showAndWait();
                        if (type.orElse(no) == yes) {
                            boolean delete = branchService.delete(data.getBranchId());
                            if (delete) {
                                return true;
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
                            btn.setCursor(Cursor.HAND);
                            btn.setStyle("-fx-background-color: #c92341");
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        tblBranch.getColumns().add(colBtn);
    }

    public void  getTable(){
        tmTableView = tblBranch;
    }

    public static TableView<BranchTm> get(){
        return tmTableView;
    }
}
