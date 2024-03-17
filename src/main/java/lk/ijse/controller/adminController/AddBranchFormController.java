package lk.ijse.controller.adminController;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lk.ijse.dto.BranchDto;
import lk.ijse.service.custom.BranchService;
import lk.ijse.service.custom.impl.BranchServiceImpl;

public class AddBranchFormController {

    @FXML
    private JFXButton addBtn;

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

    BranchService branchService = new BranchServiceImpl();
    BranchDto branchDto = new BranchDto();
    @FXML
    void addBtnOnAction(ActionEvent event) {
        branchDto.setBranchId(txtBranchId.getText());
        branchDto.setName(txtBranchName.getText());
        branchDto.setCity(txtBranchCity.getText());
        branchDto.setAddress(txtBranchAddress.getText());
        branchDto.setPostelCode(txtBranchPostalcode.getText());

        boolean save = branchService.save(branchDto);
    }

    public void initialize(){
        txtBranchId.setText(branchService.generateNewBranchId());
        txtBranchId.setEditable(false);
    }

}
