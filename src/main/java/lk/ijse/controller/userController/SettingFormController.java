package lk.ijse.controller.userController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.service.custom.UserService;
import lk.ijse.service.custom.impl.UserServiceImpl;
import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

public class SettingFormController {

    @FXML
    private Label lblConfirmPassword;

    @FXML
    private Label lblConfirmPassword1;

    @FXML
    private Label lblConfirmPassword2;

    @FXML
    private Label lblNewPassword;

    @FXML
    private Label lblNewPassword1;

    @FXML
    private Label lblNewPassword2;

    @FXML
    private AnchorPane root1;

    @FXML
    private TextField txtConfirmEmail;

    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private TextField txtConfirmUserName;

    @FXML
    private TextField txtNewEmail;

    @FXML
    private TextField txtNewPassword;

    @FXML
    private TextField txtNewUserName;

    UserService userService = new UserServiceImpl();
    @FXML
    void emailChangeBtnOnAction(ActionEvent event) {
        if (txtNewEmail.getText().equals(txtConfirmEmail.getText())){
            boolean update = userService.updateEmail(txtConfirmEmail.getText());
            if (update) {
                new Alert(Alert.AlertType.INFORMATION,"Update email").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Can't update email").show();
            }
        }
    }

    @FXML
    void passwordChangeBtnOnAction(ActionEvent event) {
        if (txtNewPassword.getText().equals(txtConfirmPassword.getText())){
            String encryptPassword = encryptPassword(txtConfirmPassword.getText());
            boolean update = userService.updatePassword(encryptPassword);
            if (update) {
                new Alert(Alert.AlertType.INFORMATION,"Update password").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Can't update password").show();
            }
        }
    }

    @FXML
    void usernameChangeBtnOnAction(ActionEvent event) {
        if (txtNewUserName.getText().equals(txtConfirmUserName.getText())){
            boolean update = userService.update(txtNewUserName.getText());
            if (update) {
                new Alert(Alert.AlertType.INFORMATION,"Update userName").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Can't update userName").show();
            }
        }
    }

    private String encryptPassword(String text) {
        String encryptValue = new String(Base64.encodeBase64(text.getBytes(StandardCharsets.UTF_8)));
        return encryptValue;
    }

}
