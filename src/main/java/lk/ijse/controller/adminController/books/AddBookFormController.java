package lk.ijse.controller.adminController.books;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import lk.ijse.dto.BookDto;

import lk.ijse.entity.Book;

import lk.ijse.service.custom.BookService;

import lk.ijse.service.custom.impl.BookServiceImpl;

import lk.ijse.util.RegExPatterns;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AddBookFormController {


    @FXML
    private CheckBox chbAuthor;

    @FXML
    private ComboBox<String> cmbGenre;


    @FXML
    private Label lblAuthorFirstnameInvalid;

    @FXML
    private Label lblAuthorLastnameInvalid;

    @FXML
    private Label lblBookIdInvalid;

    @FXML
    private Label lblBookTitleInvalid;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private TextField txtAuthorFirstName;





    @FXML
    private TextField txtBookId;

    @FXML
    private TextField txtBookTitle;

    private String imagePath;

    BookDto bookDto = new BookDto();
    BookService bookService = new BookServiceImpl();


    public void initialize() {
        getAllGenre();
        lblAuthorFirstnameInvalid.setVisible(false);
        lblAuthorLastnameInvalid.setVisible(false);
        lblBookIdInvalid.setVisible(false);
        lblBookTitleInvalid.setVisible(false);

    }

    @FXML
    void addBookImageBtnOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            imagePath = selectedFile.getAbsolutePath();
        } else {
            imagePath = null;
        }
    }

    @FXML
    void chbAuthorOnAction(ActionEvent event) {

    }

    @FXML
    void cmbGenreOnAction(ActionEvent event) {

    }


    @FXML
    void saveBtnOnAction(ActionEvent event) {
        boolean validateBookId = validateBookId(txtBookId.getText());
        bookDto.setId(Long.valueOf(txtBookId.getText()));
        bookDto.setTitle(txtBookTitle.getText());
        bookDto.setGenre(cmbGenre.getValue());
        bookDto.setImage(imagePath);
        bookDto.setStatus("Available");
        bookDto.setAuthor(txtAuthorFirstName.getText());

        boolean saveBook = bookService.save(bookDto);
        if (saveBook) {
            new Alert(Alert.AlertType.INFORMATION, "saved").show();
            clearFields();
        } else {
            new Alert(Alert.AlertType.ERROR, "not saved").show();
        }

    }


    private boolean validateBookTitle(String text) {
        boolean matches = Pattern.matches(RegExPatterns.getBookTitlePattern().pattern(), text);
        return matches;
    }

    private boolean validateBookId(String text) {
        boolean matches = Pattern.matches(RegExPatterns.getBookIdPattern().pattern(), text);
        return matches;
    }

    private boolean validateAuthor1(String text) {
        boolean matches = Pattern.matches(RegExPatterns.getAuthornamePattern().pattern(), text);
        return matches;

    }

    public void getAllGenre() {
        ObservableList<String> observableList = FXCollections.observableArrayList(
                "Action", "Adventure", "History", "Crime", "Forensic", "Biography/Autobiography", "Fantasy",
                "Mystery/Thriller", "Romance", "Horror", "Science Fiction", "Philosophy", "Biology", "Physics",
                "Chemistry", "Personal Growth", "Motivational", "Leadership", "Productivity", "Entrepreneurship",
                "Finance", "Management", "Marketing", "Christianity", "Islam", "Buddhism", "Hinduism", "New Age",
                "Sociology", "Psychology", "Anthropology", "Teaching Methods", "Educational Theory", "Translation"
        );
        cmbGenre.setItems(observableList);
    }

    public void clearFields() {
        txtBookTitle.clear();
        txtBookId.clear();
    }

}
