package lk.ijse.controller.adminController;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AdminNavigationFormController {

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private JFXButton btnBooks;

    @FXML
    private JFXButton btnBranches;

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnMembers;

    public void initialize() throws IOException {
        btnDashboardOnAction(null);
    }
    public void setForms(String forms) throws IOException {
        String[] form = {
            "/view/admin/adminDashboard_form.fxml",
            "/view/admin/books/book_form.fxml",
                "/view/admin/member/members_form.fxml",
                "/view/admin/branch_form.fxml"
        };

        JFXButton[] btn = {btnDashboard,btnBooks,btnMembers,btnBranches};
        AnchorPane load = FXMLLoader.load((getClass().getResource(forms)));
        anchorpane.getChildren().clear();
        anchorpane.getChildren().add(load);

        for (int i = 0; i < form.length; i++) {
            btn[i].setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
            if (forms.equals(form[i])) {
                btn[i].setStyle(" -fx-background-color: #ffffff;\n" +
                        "    -fx-text-fill: black;");
            }

        }
    }

    @FXML
    void btnBooksOnAction(ActionEvent event) throws IOException {
        setForms("/view/admin/books/book_form.fxml");
    }

    @FXML
    void btnBranchesOnAction(ActionEvent event) throws IOException {
        setForms("/view/admin/branch_form.fxml");
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        setForms("/view/admin/adminDashboard_form.fxml");
    }

    @FXML
    void btnMembersOnAction(ActionEvent event) throws IOException {
        setForms("/view/admin/member/members_form.fxml");
    }
}
