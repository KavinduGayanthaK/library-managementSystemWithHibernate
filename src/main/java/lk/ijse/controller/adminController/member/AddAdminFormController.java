package lk.ijse.controller.adminController.member;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.dto.AdminDto;
import lk.ijse.service.custom.AdminService;
import lk.ijse.service.custom.impl.AdminServiceImpl;
import lk.ijse.util.RegExPatterns;
import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class AddAdminFormController {

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

    AdminDto adminDto = new AdminDto();
    AdminService adminService = new AdminServiceImpl();
    @FXML
    void addBtnOnAction(ActionEvent event) {
        adminDto.setId(txtId.getText());
        boolean validateAdminName = validateAdminName(txtName.getText());
        if (validateAdminName) {
            adminDto.setName(txtName.getText());
            boolean validateEmail = validateEmail(txtEmail.getText());
            if (validateEmail) {
                adminDto.setEmail(txtEmail.getText());
                boolean validatePassword = validatePassword(txtPassword.getText());
                if (validatePassword) {
                    String password = encryptPassword(txtPassword.getText());
                    adminDto.setPassword(password);
                    boolean save = adminService.save(adminDto);
                }
            }
        }
    }
    public void initialize() {
        lblInvalidPassword.setVisible(false);
        lblInvalidEmail.setVisible(false);
        lblInvalidId.setVisible(false);
        lblInvalidName.setVisible(false);
        String adminId = generateNextId();
        txtId.setText(adminId);
        txtId.setEditable(false);
    }
    private String generateNextId() {
        String adminId = adminService.generateNewAdminId();
        return adminId;
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

    private boolean validateAdminName(String text) {
        boolean matches = Pattern.matches(RegExPatterns.getUsernamePattern().pattern(), text);
        return matches;
    }
}
