package lk.ijse.controller.adminController.books;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.dto.BookDto;
import lk.ijse.service.custom.BookService;

import lk.ijse.service.custom.impl.BookServiceImpl;



import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BookDetailsFormController {

    @FXML
    private JFXButton addBookBtn;

    @FXML
    private ComboBox<String> cmbCategory;

    @FXML
    private JFXButton addGenreBtn;


    @FXML
    private GridPane gridPane;

    @FXML
    private Label lblCategory;

    BookService bookService = new BookServiceImpl();


    @FXML
    void addBookBtnOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/admin/books/addBook_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Add Book");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void cmbCategoryOnAction(ActionEvent event) {
        String selectedCategory = cmbCategory.getValue();
        List<BookDto> allBooks = bookService.getAll();
        List<BookDto> filteredBooks = new ArrayList<>();

        for (BookDto book : allBooks) {
            String genre = book.getGenre();
            if (selectedCategory.equals("All") || genre.equals(selectedCategory)) {
                filteredBooks.add(book);
            }
        }
        loadBooks(filteredBooks);
    }
    private void loadBooks(List<BookDto> dtoList) {

        gridPane.getChildren().clear();

        List<BookDto> allBooks = bookService.getAll();

        int colomn = 0;
        int row = 0;
        for (int i = 0; i < allBooks.size(); i++) {
            OneBookDesignFormController.index = i;
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/view/admin/books/oneBookDesign_form.fxml"));
                gridPane.add(parent, colomn, row++);

                GridPane.setMargin(parent, new Insets(5, 5, 5, 5));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
    public void initialize(){
        setCmbCategoryValues();
    }
    private void setCmbCategoryValues() {
        ObservableList<String> observableList = FXCollections.observableArrayList(
                "All","Action","Adventure","History","Crime","Forensic","Biography","Fantasy",
                "Mystery","Thriller","Romance","Horror","Science Fiction","Philosophy","Biology", "Physics",
                "Chemistry","Personal Growth","Motivational","Leadership","Productivity","Entrepreneurship",
                "Finance","Management","Marketing","Christianity", "Islam","Buddhism","Hinduism","New Age",
                "Sociology", "Psychology","Anthropology","Teaching Methods","Educational Theory","Translation");
        cmbCategory.setItems(observableList);
    }



}
