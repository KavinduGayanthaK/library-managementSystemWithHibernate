package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.dto.AdminDto;
import lk.ijse.dto.UserDto;
import lk.ijse.entity.User;
import lk.ijse.service.custom.AdminService;
import lk.ijse.service.custom.UserService;
import lk.ijse.service.custom.impl.AdminServiceImpl;
import lk.ijse.service.custom.impl.UserServiceImpl;
import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import java.util.List;

public class LoginFormController {

    @FXML
    private Label lblInvalidPassword;

    @FXML
    private Label lblInvalidUserName;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    public static User user = new User();

    @FXML
    private Hyperlink signIn;
    UserService userService = new UserServiceImpl();
    AdminService adminService = new AdminServiceImpl();

    public void initialize() {
        lblInvalidUserName.setVisible(false);
        lblInvalidPassword.setVisible(false);
    }

    @FXML
    void loginBtnOnAction(ActionEvent event) throws IOException {
        String enteredUsername = txtUsername.getText();
        String enteredPassword = encryptPassword(txtPassword.getText());

        for (UserDto userDto : userService.getAll1()) {
            if (userDto.getUsername().equals(enteredUsername) && userDto.getPassword().equals(enteredPassword)) {
                user.setId(userDto.getId());
                user.setUsername(userDto.getUsername());
                user.setEmail(userDto.getEmail());
                user.setPassword(userDto.getPassword());
                loadHomePage();
                return;
            }
        }

        for (AdminDto adminDto : adminService.getAll()) {
            if (adminDto.getName().equals(enteredUsername) && adminDto.getPassword().equals(enteredPassword)) {
                loadAdminPage();
                return;
            }
        }

        lblInvalidUserName.setVisible(true);
        lblInvalidPassword.setVisible(true);
    }

    private void loadAdminPage() throws IOException {
        Stage stage1 = (Stage) loginBtn.getScene().getWindow();
        stage1.close();
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/admin/adminNavigation_form.fxml"))));
        stage.show();
    }

    private void loadHomePage() throws IOException {
        Stage stage1 = (Stage) loginBtn.getScene().getWindow();
        stage1.close();
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/user/userNavigation_form.fxml"))));
        stage.show();
    }

    private String encryptPassword(String text) {
        String encryptPassword = new String(Base64.encodeBase64(text.getBytes(StandardCharsets.UTF_8)));
        return encryptPassword;
    }

    @FXML
    void signInOnAction(ActionEvent event) throws IOException {
        Stage stage1 = (Stage) signIn.getScene().getWindow();
        stage1.close();
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/registerUser_form.fxml"))));
        stage.show();

    }

}
