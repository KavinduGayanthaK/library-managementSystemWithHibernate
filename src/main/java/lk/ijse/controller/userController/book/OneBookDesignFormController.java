package lk.ijse.controller.userController.book;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.controller.userController.book.BurrowBookFormController;

import java.io.IOException;
import java.net.URL;

public class OneBookDesignFormController {

    @FXML
    private ImageView imgBook;

    @FXML
    private Label lblBookId;

    @FXML
    private Label lblBookTitle;

    @FXML
    private Label lblStatus;

    @FXML
    void burrowBtnOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/user/book/burrowBook_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        BurrowBookFormController burrowBookFormController = fxmlLoader.getController();
        burrowBookFormController.getLblBookTitle().setText("Book Title : "+lblBookTitle.getText());
        burrowBookFormController.getLblBookId().setText("Book Id : "+lblBookId.getText());
        stage.setTitle("License Details");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();

    }

    @FXML
    void showDetailOnAction(MouseEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/user/book/viewOneBookDetails_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Book Details");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

    public Label getTitle() {
        return lblBookTitle;
    }

    public Label getStatus() {
        return lblStatus;
    }

    public ImageView getImgBook() {
        return imgBook;
    }

    public Label getBookId() {
        return lblBookId;
    }
}
