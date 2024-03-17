package lk.ijse.controller.adminController.member;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.dto.UserDto;
import lk.ijse.service.custom.UserService;
import lk.ijse.service.custom.impl.UserServiceImpl;
import lk.ijse.util.RegExPatterns;
import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class AddMemberFormController {

    @FXML
    private JFXButton addBtn;

    @FXML
    private Label lblInvalidEmail;

    @FXML
    private Label lblInvalidId;

    @FXML
    private Label lblInvalidName;

    @FXML
    private Label lblInvalidPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    UserDto userDto = new UserDto();
    UserService userService = new UserServiceImpl();
    @FXML
    void addBtnOnAction(ActionEvent event) {
        userDto.setId(txtId.getText());
        boolean validateUserName = validateUserName(txtName.getText());
        if (validateUserName) {
            userDto.setUsername(txtName.getText());
            boolean validateEmail = validateEmail(txtEmail.getText());
            if (validateEmail) {
                userDto.setEmail(txtEmail.getText());

                boolean validatePassword = validatePassword(txtPassword.getText());
                if (validatePassword) {
                    String password = encryptPassword(txtPassword.getText());
                    userDto.setPassword(password);

                    boolean save = userService.save(userDto);
                    if (save) {
                        new Alert(Alert.AlertType.INFORMATION,"Successful Saved").show();

                    }
                }else {
                    lblInvalidPassword.setVisible(true);
                }
            } else {
                lblInvalidEmail.setVisible(true);
            }
        } else {
            lblInvalidName.setVisible(true);
        }
    }

    public void initialize() {
        lblInvalidPassword.setVisible(false);
        lblInvalidEmail.setVisible(false);
        lblInvalidId.setVisible(false);
        lblInvalidName.setVisible(false);
        String userID = generateNextId();
        txtId.setText(userID);
        txtId.setEditable(false);
    }
    private String generateNextId() {
        String userId = userService.generateNewUserId();
        return userId;
    }
    private String encryptPassword(String text) {
        String encryptValue = new String(Base64.encodeBase64(text.getBytes(StandardCharsets.UTF_8)));
        return encryptValue;
    }

    private boolean validatePassword(String text) {
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

}
