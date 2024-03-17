package lk.ijse.controller.adminController.books;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.dto.BookDto;
import lk.ijse.service.custom.BookService;
import lk.ijse.service.custom.impl.BookServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OneBookDesignFormController {
    @FXML
    private Label lblBookId;

    @FXML
    private JFXButton btnQuickAdd;

    @FXML
    private ImageView imageView;

    @FXML
    private Label lblAuthor;

    @FXML
    private Label lblBookTitle;

    @FXML
    private Label lblGenre;

    @FXML
    private Label lblStatus;

    BookService bookService = new BookServiceImpl();
    public static int index;
    List<BookDto> books = new ArrayList<>();
    @FXML
    void btnQuickAddOnAction(ActionEvent event) {

    }

    @FXML
    void quickAddLabelMouseEnterOnAction(MouseEvent event) {

    }

    @FXML
    void quickAddLabelMouseExitOnAction(MouseEvent event) {

    }

    public void initialize() {
        btnQuickAdd.setVisible(false);
        setDataInGridPane();
    }

    private void setDataInGridPane() {
        books = bookService.getAll();

        Image image = new Image(books.get(index).getImage());
        ImageView imageView1 = new ImageView(image);

        imageView.setImage(imageView1.getImage());
        lblBookTitle.setText(books.get(index).getTitle());
        lblAuthor.setText(books.get(index).getAuthor());
        lblGenre.setText(books.get(index).getGenre());
        lblStatus.setText(books.get(index).getStatus());
        lblBookId.setText(String.valueOf(books.get(index).getId()));
    }
    @FXML
    void showDetailOnAction(MouseEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/admin/books/viewOneBookDetails_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        ViewOneBookDetailsFormController controller = fxmlLoader.getController();
        controller.getTxtBookTitle().setText(lblBookTitle.getText());
        controller.getTxtGenre().setText(lblGenre.getText());
        controller.getTxtAuthorName().setText(lblAuthor.getText());
       // controller.getTxtBookId().setText();
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Book Details");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }
//    private void getClickBookDetails() {
//        try {
//            Optional<BookDto> first = books.stream().filter(books1 -> books1.getTitle() == lblBookTitle.getText()).findFirst();
//            QuickAddFormController.title = first.get().getTitle();
//            QuickAddFormController.bookId = first.get().getId();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
