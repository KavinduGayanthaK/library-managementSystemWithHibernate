package lk.ijse.controller.userController;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class UserNavigationFormController {

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private JFXButton btnBooks;

    @FXML
    private JFXButton btnDashboard;
    @FXML
    private JFXButton btnSetting;



    public void initialize() throws IOException {
        btnDashboardOnAction(null);
    }

    @FXML
    void btnBooksOnAction(ActionEvent event) throws IOException {
        setForms("/view/user/book/book_form.fxml");
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        setForms("/view/user/userDashboard_form.fxml");
    }
    @FXML
    void btnSettingOnAction(ActionEvent event) throws IOException {
        setForms("/view/user/book/setting_form.fxml");
    }

    public void setForms(String forms) throws IOException {
        String[] form = {
                "/view/user/userDashboard_form.fxml",
                "/view/user/book/book_form.fxml",
                "/view/user/book/setting_form.fxml"
        };

        JFXButton[] btn = {btnDashboard,btnBooks,btnSetting};
        AnchorPane load = FXMLLoader.load((getClass().getResource(forms)));
        anchorpane.getChildren().clear();
        anchorpane.getChildren().add(load);
    }


}
