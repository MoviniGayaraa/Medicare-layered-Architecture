package lk.ijse.pharmacy.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.pharmacy.bo.BOFactory;
import lk.ijse.pharmacy.bo.custom.CustomerBO;
import lk.ijse.pharmacy.bo.custom.impl.CustomerBOImpl;
import lk.ijse.pharmacy.dao.DAOFactory;
import lk.ijse.pharmacy.dto.CustomerDTO;

import lk.ijse.pharmacy.tm.CustomerTm;
import lk.ijse.pharmacy.util.AlertController;
import lk.ijse.pharmacy.util.ValidateField;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerFormController {

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TableColumn<?, ?> colCusId;

    @FXML
    private TableColumn<?, ?> colFName;

    @FXML
    private TableColumn<?, ?> colLName;

    @FXML
    private TableColumn<?, ?> colStreet;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colLane;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TextField txtSearch;

    @FXML
    private ImageView imgSearch;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtStreet;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtLane;

    @FXML
    private TextField txtContact;

    @FXML
    private Label lblinvalidcustid;

    @FXML
    private Label lblinvalidcontact;


    CustomerBOImpl customerBO = new CustomerBOImpl();


    @FXML
    void CustomerAddOnAction(ActionEvent event) {
        if(txtId.getText().isEmpty()||txtFirstName.getText().isEmpty()||txtLastName.getText().isEmpty()||txtStreet.getText().isEmpty()||txtCity.getText().isEmpty()||txtLane.getText().isEmpty()||txtContact.getText().isEmpty()){

            AlertController.errormessage("Fill the all feald");
        }else {
            if (ValidateField.custIdCheck(txtId.getText())||ValidateField.contactCheck(txtContact.getText())) {
                if(ValidateField.custIdCheck(txtId.getText())){
                    lblinvalidcustid.setVisible(false);
                    if(ValidateField.contactCheck(txtContact.getText())){
                        lblinvalidcustid.setVisible(false);
                        lblinvalidcontact.setVisible(false);

                        String custId = txtId.getText();
                        String fristName = txtFirstName.getText();
                        String lastName = txtLastName.getText();
                        String street = txtStreet.getText();
                        String city = txtCity.getText();
                        String lane = txtLane.getText();
                        String contact = txtContact.getText();

                        CustomerDTO customerDTO = new CustomerDTO(custId,fristName,lastName,street,city,lane,contact);

                        try {
                            boolean isSaved = customerBO.saveCustomer(customerDTO);

                            if (isSaved) {
//                setCellValueFactory();
                                getAll();
//                clearTxtField();
                                AlertController.confirmmessage("Customer added successfuly");
                            } else {
                                AlertController.errormessage("error");
                            }
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }else {
                    lblinvalidcustid.setVisible(true);
                }

            }else {
                lblinvalidcustid.setVisible(true);
                lblinvalidcontact.setVisible(true);
            }
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws ClassNotFoundException {
        String cusId = txtId.getText();

        try {
            boolean isSave = customerBO.deleteCustomer(cusId);
            if(isSave){
                AlertController.confirmmessage("Success");
                getAll();
            }

        }catch (SQLException e){
            AlertController.errormessage("not Succses");
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws ClassNotFoundException {
        String custId = txtId.getText();
        String fristName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String street = txtStreet.getText();
        String city = txtCity.getText();
        String lane = txtLane.getText();
        String contact = txtContact.getText();

        CustomerDTO customerDTO = new CustomerDTO(custId,fristName,lastName,street,city,lane,contact);

        try {
            Boolean isSave = customerBO.updateCustomer(customerDTO);
            if(isSave){
                getAll();
                AlertController.confirmmessage("Updated");
            }

        }catch (SQLException e){
            AlertController.errormessage("not Updated");
        }

    }

    @FXML
    void imgSearchOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void searchOnAction(ActionEvent event) {

    }

    @FXML
    void tblCustomerOnMouseClicked(MouseEvent event) {
        TablePosition pos=tblCustomer.getSelectionModel().getSelectedCells().get(0);
        int row=pos.getRow();

        ObservableList<TableColumn<CustomerTm,?>> columns=tblCustomer.getColumns();

        txtId.setText(columns.get(0).getCellData(row).toString());
        txtFirstName.setText(columns.get(1).getCellData(row).toString());
        txtLastName.setText(columns.get(2).getCellData(row).toString());
        txtStreet.setText((columns.get(3).getCellData(row).toString()));
        txtCity.setText(columns.get(4).getCellData(row).toString());
        txtLane.setText(columns.get(5).getCellData(row).toString());
        txtContact.setText(columns.get(6).getCellData(row).toString());

    }

    @FXML
    void txtContactOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void txtIdOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void txtSearchKeyTyped(KeyEvent event) {

    }
    @FXML
    void initialize() {
        lblinvalidcustid.setVisible(false);
        lblinvalidcontact.setVisible(false);
        setCellValueFactory();
        getAll();
    }

    private void setCellValueFactory() {
        colCusId.setCellValueFactory(new PropertyValueFactory<>("custID"));
        colFName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colStreet.setCellValueFactory(new PropertyValueFactory<>("street"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colLane.setCellValueFactory(new PropertyValueFactory<>("lane"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    public void getAll() {


       tblCustomer.getItems().clear();
        try {
            ArrayList<CustomerDTO> supList = customerBO.getAllCustomer();
            for (CustomerDTO i : supList) {
                tblCustomer.getItems().add(new CustomerTm(i.getCustID(), i.getFirstName(), i.getLastName(), i.getStreet(),i.getCity(),i.getLane(),i.getContact()));
                System.out.println(i.getCustID()+""+ i.getFirstName()+""+ i.getLastName()+""+  i.getStreet()+""+ i.getCity()+""+ i.getLane()+""+ i.getContact());
            }
            //mainCOMCustomer.setItems(supList);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
            AlertController.errormessage("something went wrong!");
        }
    }

}
