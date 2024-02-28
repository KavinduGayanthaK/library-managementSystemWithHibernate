package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.dto.UserDto;
import lk.ijse.service.custom.UserService;
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

    UserService userService = new UserServiceImpl();

    public void initialize() {
        lblInvalidUserName.setVisible(false);
        lblInvalidPassword.setVisible(false);
    }

    @FXML
    void loginBtnOnAction(ActionEvent event) throws IOException {
       for (UserDto userDto : userService.getAll()){
           if (txtUsername.getText().equals(userDto.getUsername())) {
               String passwordEncrypt = encryptPassword(txtPassword.getText());
               if(passwordEncrypt.equals(userDto.getPassword())){
                   loadHomePage();
               }else {
                   lblInvalidPassword.setVisible(true);
               }
           }else {
              lblInvalidUserName.setVisible(true);
           }
       }
    }

    private void loadHomePage() throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/admin/adminNavigation_form.fxml"))));
        stage.show();
    }

    private String encryptPassword(String text) {
        String encryptPassword = new String(Base64.encodeBase64(text.getBytes(StandardCharsets.UTF_8)));
        return encryptPassword;
    }

    @FXML
    void signInOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/registerUser_form.fxml"))));
        stage.show();
    }

}
