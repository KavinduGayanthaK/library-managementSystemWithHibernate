package lk.ijse.controller;




import org.apache.commons.codec.binary.Base64;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.dto.UserDto;
import lk.ijse.service.custom.UserService;
import lk.ijse.service.custom.impl.UserServiceImpl;
import lk.ijse.util.RegExPatterns;

import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;



public class RegisterUserFormController {

    @FXML
    private Label lblInvalidUserName;

    @FXML
    private Label lblInvalidEmail;

    @FXML
    private Label lblInvalidPassword;

    @FXML
    private Label lblPassword;

    @FXML
    private Label lblUsername;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    UserDto userDto = new UserDto();
    UserService userService = new UserServiceImpl();

    public void initialize() {
        lblPassword.setVisible(false);
        lblUsername.setVisible(false);
        lblInvalidUserName.setVisible(false);
        lblInvalidPassword.setVisible(false);
        lblInvalidEmail.setVisible(false);
    }

    @FXML
    void registerBtnOnAction(ActionEvent event) throws IOException {
        String userId = generateNextId();
        userDto.setId(userId);
        boolean validateUserName = validateUserName(txtUsername.getText());
        if (validateUserName) {
            userDto.setUsername(txtUsername.getText());
            lblUsername.setVisible(false);

            boolean validateEmail = validateEmail(txtEmail.getText());
            if (validateEmail) {
                userDto.setEmail(txtEmail.getText());


                boolean validatePassword = validatePasword(txtPassword.getText());
                if (validatePassword) {
                    String password = encriptPassword(txtPassword.getText());
                    userDto.setPassword(password);
                    lblPassword.setVisible(false);
                    boolean save = userService.save(userDto);
                    if (save) {
                        new Alert(Alert.AlertType.INFORMATION,"Successful SignIn").show();
                        loadLoginPage();
                    }
                }else {
                    lblInvalidPassword.setVisible(true);
                }
            } else {
                lblInvalidEmail.setVisible(true);
            }
        } else {
            lblInvalidUserName.setVisible(true);
        }
    }

    private String generateNextId() {
        String userId = userService.generateNewUserId();
        return userId;
    }
    private String encriptPassword(String text) {
        String encryptValue = new String(Base64.encodeBase64(text.getBytes(StandardCharsets.UTF_8)));
        return encryptValue;
    }

    private void loadLoginPage() throws IOException {
        Stage stage1 = (Stage) loginBtn.getScene().getWindow();
        stage1.close();
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/login_form.fxml"))));
        stage.show();
    }

    private boolean validatePasword(String text) {
        boolean matches = Pattern.matches(RegExPatterns.getPasswordPattern().pattern(), text);
        return matches;
    }

    private boolean validateEmail(String text) {
        boolean matches = Pattern.matches(RegExPatterns.getEmailPattern().pattern(), text);
        return matches;
    }

    private boolean validateUserName(String text) {
        boolean matches = Pattern.matches(RegExPatterns.getUsernamePattern().pattern(), text);
        return matches;
    }

    @FXML
    void txtPasswordOnMouseClickedAction(MouseEvent event) {
        lblUsername.setVisible(false);
        lblPassword.setVisible(true);
    }

    @FXML
    void txtUsernameOnMouseClickedAction(MouseEvent event) {
        lblPassword.setVisible(false);
        lblUsername.setVisible(true);
    }

}
