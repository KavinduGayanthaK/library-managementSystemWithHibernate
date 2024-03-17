package lk.ijse.controller.adminController.books;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import lk.ijse.dto.BookDto;
import lk.ijse.service.custom.BookService;
import lk.ijse.service.custom.impl.BookServiceImpl;

public class ViewOneBookDetailsFormController {

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;


    @FXML
    private ComboBox<String> cmbGenre;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblTitle;

    @FXML
    private ImageView lmgBook;

    @FXML
    private TextField txtAuthorName;
    @FXML
    private TextField txtGenre;

    @FXML
    private TextField txtBookId;

    @FXML
    private TextField txtBookTitle;

    BookService bookService = new BookServiceImpl();
    BookDto bookDto = new BookDto();

    public void initialize(){
        getAllGenre();
        txtGenre.setEditable(false);
    }
    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        boolean delete = bookService.delete(txtBookId.getText());
        if (delete){
            new Alert(Alert.AlertType.INFORMATION,"Delete book").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Can't delete book").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        bookDto.setId(Long.parseLong(txtBookId.getText()));
        bookDto.setAuthor(txtAuthorName.getText());
        bookDto.setStatus(lblStatus.getText());
        bookDto.setGenre(cmbGenre.getValue());
        bookDto.setTitle(txtBookTitle.getText());

        boolean update = bookService.update(bookDto);
        if (update){
            new Alert(Alert.AlertType.INFORMATION,"Update book").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Can't update book").show();
        }
    }



    @FXML
    void cmbGenreOnAction(ActionEvent event) {

    }

    public Label getLblStatus() {
        return lblStatus;
    }

    public Label getLblTitle() {
        return lblTitle;
    }

    public TextField getTxtAuthorName() {
        return txtAuthorName;
    }

    public TextField getTxtBookId() {
        return txtBookId;
    }

    public TextField getTxtBookTitle() {
        return txtBookTitle;
    }
    public TextField getTxtGenre() {
        return txtGenre;
    }



    public ImageView getImgBook() {
        return lmgBook;
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
}
