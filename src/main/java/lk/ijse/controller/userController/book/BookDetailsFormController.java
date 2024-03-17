package lk.ijse.controller.userController.book;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import lk.ijse.dto.BookDto;
import lk.ijse.service.custom.BookService;
import lk.ijse.service.custom.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class BookDetailsFormController {

    @FXML
    private ComboBox<String> cmbCategory;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label lblCategory;
    BookService bookService = new BookServiceImpl();
    @FXML
    void cmbCategoryOnAction(ActionEvent event) {
        String selectedCategory = cmbCategory.getValue();
        List<BookDto> allBooks = bookService.getAll();
        List<BookDto> filteredBooks = new ArrayList<>();

        for (BookDto book : allBooks) {
            String genre = book.getGenre();
            if (selectedCategory.equals("All") || genre.equals(selectedCategory)) {
                if ( book.getStatus().equals("Available"))
                        filteredBooks.add(book);
            }
        }
        loadBooks(filteredBooks);
    }

    private void loadBooks(List<BookDto> dtoList) {

        gridPane.getChildren().clear();
        int y= 0;
        int x = 0;
        for (int i = 0; i < dtoList.size(); i++) {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user/book/oneBookDesign_form.fxml"));
                Parent parent = loader.load();
                OneBookDesignFormController controller =loader.getController();
                Long id = dtoList.get(i).getId();
                controller.getBookId().setText(String.valueOf(id));
                controller.getTitle().setText(dtoList.get(i).getTitle());
                controller.getStatus().setText(dtoList.get(i).getStatus());
                Image image = new Image("file:" + dtoList.get(i).getImage());
                controller.getImgBook().setImage(image);

                if(x/4==1){x=0;y=y+1;}

                gridPane.add(parent, x, y);

                x++;
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public void initialize(){
        setCmbCategoryValues();
    }
    private void setCmbCategoryValues() {
        ObservableList<String> observableList = FXCollections.observableArrayList(
                "All","Action","Adventure","History","Crime","Forensic","Biography/Autobiography","Fantasy",
                "Mystery/Thriller","Romance","Horror","Science Fiction","Philosophy","Biology", "Physics",
                "Chemistry","Personal Growth","Motivational","Leadership","Productivity","Entrepreneurship",
                "Finance","Management","Marketing","Christianity", "Islam","Buddhism","Hinduism","New Age",
                "Sociology", "Psychology","Anthropology","Teaching Methods","Educational Theory","Translation");
        cmbCategory.setItems(observableList);
    }

}
