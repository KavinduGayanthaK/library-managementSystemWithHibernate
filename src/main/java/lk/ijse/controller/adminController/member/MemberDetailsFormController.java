package lk.ijse.controller.adminController.member;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.service.custom.AdminService;
import lk.ijse.service.custom.UserService;
import lk.ijse.service.custom.impl.AdminServiceImpl;
import lk.ijse.service.custom.impl.UserServiceImpl;
import lk.ijse.tm.AdminTm;
import lk.ijse.tm.UserTm;

import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.stream.Collectors;

public class MemberDetailsFormController {

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<AdminTm> tblAdmin;

    @FXML
    private TableView<UserTm> tblMember;

     UserService userService = new UserServiceImpl();
     AdminService adminService = new AdminServiceImpl();
    @FXML
    void addAdminBtnOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/admin/member/addAdmin_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Add Admin");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void addMemberBtnOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/admin/member/addMember_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Add Member");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

    public void initialize(){
        tblMember.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblMember.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("username"));
        tblMember.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("email"));
        loadUsers();

        tblAdmin.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblAdmin.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("username"));
        tblAdmin.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("email"));
        loadAdmin();
    }

    private  void loadUsers() {

        List<UserTm> userTmList = userService.getAll1().stream().map(user->new UserTm(
                user.getId(), user.getUsername(), user.getEmail())).collect(Collectors.toList());

        ObservableList<UserTm> observableList = FXCollections.observableArrayList(userTmList);
        tblMember.setItems(observableList);
    }
    private  void loadAdmin() {

        List<AdminTm> adminTmList = adminService.getAll1().stream().map(adminDto->new AdminTm(
                adminDto.getId(), adminDto.getName(), adminDto.getEmail())).collect(Collectors.toList());

        ObservableList<AdminTm> observableList = FXCollections.observableArrayList(adminTmList);
        tblAdmin.setItems(observableList);
    }


}
