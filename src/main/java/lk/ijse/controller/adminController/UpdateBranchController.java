package lk.ijse.controller.adminController;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.dto.BranchDto;
import lk.ijse.service.custom.BranchService;
import lk.ijse.service.custom.impl.BranchServiceImpl;

import java.io.IOException;

public class UpdateBranchController {

    @FXML
    private TextField txtBranchAddress;

    @FXML
    private TextField txtBranchCity;

    @FXML
    private TextField txtBranchId;

    @FXML
    private TextField txtBranchName;

    @FXML
    private TextField txtBranchPostalcode;

    @FXML
    private JFXButton updateBtn;

    BranchDto branchDto = new BranchDto();
    BranchService branchService = new BranchServiceImpl();
    @FXML
    void updateBtnOnAction(ActionEvent event) throws IOException {
        branchDto.setBranchId(txtBranchId.getText());
        branchDto.setName(txtBranchName.getText());
        branchDto.setCity(txtBranchCity.getText());
        branchDto.setAddress(txtBranchAddress.getText());
        branchDto.setPostelCode(txtBranchPostalcode.getText());

        boolean update = branchService.updateBranch(branchDto);
        if (update) {
            Stage stage = (Stage) updateBtn.getScene().getWindow();
            stage.close();
            BranchFormController.get().refresh();
            new Alert(Alert.AlertType.INFORMATION,"Update branch").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Can't update branch").show();
        }
    }

    public void initialize(){
        txtBranchId.setText(BranchFormController.branchTm.getBranchId());
        txtBranchId.setEditable(false);
        txtBranchName.setText(BranchFormController.branchTm.getName());
        txtBranchCity.setText(BranchFormController.branchTm.getCity());
        txtBranchAddress.setText(BranchFormController.branchTm.getAddress());
        txtBranchPostalcode.setText(BranchFormController.branchTm.getPostelCode());
    }

}
