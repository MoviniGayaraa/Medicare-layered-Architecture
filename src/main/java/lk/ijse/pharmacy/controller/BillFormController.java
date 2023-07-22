package lk.ijse.pharmacy.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.pharmacy.bo.custom.impl.BillBOImpl;
import lk.ijse.pharmacy.dto.BillDTO;
import lk.ijse.pharmacy.dto.CustomerDTO;

import lk.ijse.pharmacy.tm.BillTm;
import lk.ijse.pharmacy.tm.CustomerTm;
import lk.ijse.pharmacy.util.AlertController;
import lk.ijse.pharmacy.util.ValidateField;

public class BillFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> BillIdCol;

    @FXML
    private TableColumn<?, ?> OrderIdCol;

    @FXML
    private TableColumn<?, ?> TotalAmtCol;

    @FXML
    private TableColumn<?, ?> CustPayCol;

    @FXML
    private TableColumn<?, ?> OrderDateCol;

    @FXML
    private TextField searchText;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private TextField BillIdText;

    @FXML
    private TextField AmountText;

    @FXML
    private TextField PayemntText;

    @FXML
    private TextField DiscountText;

    @FXML
    private ComboBox OrderIdBox;

    @FXML
    private DatePicker OrderDateText;

    @FXML
    private TableView<BillTm> BillTbl;

    @FXML
    private TableColumn<?, ?> DiscountCol;

    BillBOImpl billBO = new BillBOImpl();

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        if(ValidateField.billIdCheck(BillIdText.getText())) {
            if(ValidateField.priceCheck(AmountText.getText())) {
                if(ValidateField.priceCheck(PayemntText.getText())) {
                    if(ValidateField.priceCheck(DiscountText.getText())) {


                        BillDTO billDTO = new BillDTO();

                        billDTO.setBillID(BillIdText.getText());
                        billDTO.setOrderID(String.valueOf(OrderIdBox.getValue()));
                        billDTO.setTotalAmt(Double.parseDouble(AmountText.getText()));
                        billDTO.setCustPay(Double.parseDouble(PayemntText.getText()));
                        billDTO.setDiscount(Double.parseDouble(DiscountText.getText()));
                        billDTO.setOrderDate(Date.valueOf(OrderDateText.getValue()));

                        try {
                            boolean isSave = billBO.save(billDTO);
                            if (isSave) {
                                AlertController.confirmmessage("Save successFully");

                                getAll();
                                setCellValueFactory();

                                BillIdText.setText("");
                                OrderIdBox.setValue(null);
                                AmountText.setText(null);
                                PayemntText.setText("");
                                DiscountText.setText("");
                                OrderDateText.setValue(null);



                            }


                        } catch (Exception e) {
                            System.out.println(e);
                            AlertController.errormessage("Error");

                        }
                    }else{
                        AlertController.errormessage("invalied Discount");
                    }
                }else{
                    AlertController.errormessage("invalied Payment");
                }
            }else{
                AlertController.errormessage("invalied Amount");
            }
        }else{
            AlertController.errormessage("invalied Id");
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws ClassNotFoundException {

        String billId=BillIdText.getText();

        boolean result = AlertController.okconfirmmessage("Are you sure you want to remove this Bill?");
        if(result==true){

            try {
                boolean isDeleted = billBO.delete(billId);
                if (isDeleted) {
                    AlertController.confirmmessage("Delete successFully");

                    getAll();
                    BillIdText.setText("");
                    OrderIdBox.setValue(null);
                    AmountText.setText(null);
                    PayemntText.setText("");
                    DiscountText.setText("");
                    OrderDateText.setValue(null);



                } else {
                    AlertController.errormessage("Somethink went wrong");
                }
            }catch (SQLException throwables){
                throwables.printStackTrace();
                AlertController.errormessage("Somethink went wrong");

            }
        }


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws ClassNotFoundException {

        if(ValidateField.billIdCheck(BillIdText.getText())) {
            if(ValidateField.priceCheck(AmountText.getText())) {
                if(ValidateField.priceCheck(PayemntText.getText())) {
                    if(ValidateField.priceCheck(DiscountText.getText())) {

        BillDTO billDTO =new BillDTO();

        billDTO.setBillID(BillIdText.getText());
        billDTO.setOrderID(String.valueOf(OrderIdBox.getValue()));
        billDTO.setTotalAmt(Double.parseDouble(AmountText.getText()));
        billDTO.setCustPay(Double.parseDouble(PayemntText.getText()));
        billDTO.setDiscount(Double.parseDouble(DiscountText.getText()));
        billDTO.setOrderDate(Date.valueOf(OrderDateText.getValue()));


        boolean result = AlertController.okconfirmmessage("Are you sure you want to Update  this Bill?");
        if (result == true) {


            try {
                boolean isUpdates = billBO.update(billDTO);
                if (isUpdates) {
                    AlertController.confirmmessage("Update successFully");
                    getAll();

                    BillIdText.setText("");
                    OrderIdBox.setValue(null);
                    AmountText.setText(null);
                    PayemntText.setText("");
                    DiscountText.setText("");
                    OrderDateText.setValue(null);


                } else {

                    AlertController.errormessage("Somethink went wrong");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                AlertController.errormessage("Somethink went wrong");
            }


        }
                    }else{
                        AlertController.errormessage("invalied Discount");
                    }
                }else{
                    AlertController.errormessage("invalied Payment");
                }
            }else{
                AlertController.errormessage("invalied Amount");
            }
        }else{
            AlertController.errormessage("invalied Id");
        }

    }

    public void SearchMouseClick(MouseEvent mouseEvent) throws ClassNotFoundException {


        String itemId = searchText.getText();

        try {
            ArrayList<BillDTO> billDTO = billBO.search(itemId);

            for (BillDTO b: billDTO) {

                BillIdText.setText(b.getBillID());
                OrderIdBox.setValue(b.getOrderID());
                AmountText.setText(String.valueOf(b.getTotalAmt()));
                PayemntText.setText(String.valueOf(b.getCustPay()));
                DiscountText.setText(String.valueOf(b.getDiscount()));

            }

            /*String searchValue = BillIdText.getText().trim();
            ObservableList<BillTm> obList = FXCollections.observableArrayList();
*/
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            AlertController.errormessage("Somethink went wrong");

        }

    }

    @FXML
    void txtIdOnMouseClicked(MouseEvent event) {

    }

    private void LoadOrderId() throws ClassNotFoundException {

        try{
            ObservableList<String> orderIds= FXCollections.observableArrayList();
            List<String> ids = billBO.LoadOrderIds();

            for(String id:ids) {
                orderIds.add(id);

            }
            OrderIdBox.setItems(orderIds);

        }catch (SQLException throwables){
            AlertController.errormessage("Something went Wrong");
        }
    }

    private void getAll() throws SQLException, ClassNotFoundException {
        BillTbl.getItems().clear();
        try {
            ArrayList<BillDTO> billData=billBO.getAll();

            for (BillDTO i : billData) {
                BillTbl.getItems().add(new BillTm(i.getBillID(), i.getOrderID(), i.getTotalAmt(), i.getCustPay(),i.getDiscount(),i.getOrderDate()));
                }

            //BillTbl.setItems(billData);
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    private void setCellValueFactory(){

        BillIdCol.setCellValueFactory(new PropertyValueFactory<>("billID"));
        OrderIdCol.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        TotalAmtCol.setCellValueFactory(new PropertyValueFactory<>("totalAmt"));
        CustPayCol.setCellValueFactory(new PropertyValueFactory<>("custPay"));
        OrderDateCol.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        DiscountCol.setCellValueFactory(new PropertyValueFactory<>("discount"));

    }


    @FXML
    void initialize() {

        try {
            LoadOrderId();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        setCellValueFactory();


        assert BillIdCol != null : "fx:id=\"BillIdCol\" was not injected: check your FXML file 'Bill.fxml'.";
        assert OrderIdCol != null : "fx:id=\"OrderIdCol\" was not injected: check your FXML file 'Bill.fxml'.";
        assert TotalAmtCol != null : "fx:id=\"TotalAmtCol\" was not injected: check your FXML file 'Bill.fxml'.";
        assert CustPayCol != null : "fx:id=\"CustPayCol\" was not injected: check your FXML file 'Bill.fxml'.";
        assert OrderDateCol != null : "fx:id=\"OrderDateCol\" was not injected: check your FXML file 'Bill.fxml'.";
        assert searchText != null : "fx:id=\"searchText\" was not injected: check your FXML file 'Bill.fxml'.";
        assert btnUpdate != null : "fx:id=\"btnUpdate\" was not injected: check your FXML file 'Bill.fxml'.";
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'Bill.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'Bill.fxml'.";
        assert BillIdText != null : "fx:id=\"BillIdText\" was not injected: check your FXML file 'Bill.fxml'.";
        assert AmountText != null : "fx:id=\"AmountText\" was not injected: check your FXML file 'Bill.fxml'.";
        assert PayemntText != null : "fx:id=\"PayemntText\" was not injected: check your FXML file 'Bill.fxml'.";
        assert DiscountText != null : "fx:id=\"DiscountText\" was not injected: check your FXML file 'Bill.fxml'.";
        assert OrderIdBox != null : "fx:id=\"OrderIdBox\" was not injected: check your FXML file 'Bill.fxml'.";
        assert OrderDateText != null : "fx:id=\"OrderDateText\" was not injected: check your FXML file 'Bill.fxml'.";

    }

    @FXML
    void BillMouseClick(MouseEvent event) {
        TablePosition pos=BillTbl.getSelectionModel().getSelectedCells().get(0);
        int row=pos.getRow();

        ObservableList<TableColumn<BillTm,?>> columns=BillTbl.getColumns();


        BillIdText.setText(columns.get(0).getCellData(row).toString());
        OrderIdBox.setValue(columns.get(1).getCellData(row).toString());
        AmountText.setText(columns.get(2).getCellData(row).toString());
        PayemntText.setText(columns.get(3).getCellData(row).toString());
        DiscountText.setText(columns.get(5).getCellData(row).toString());
        OrderDateText.setValue(LocalDate.parse(columns.get(4).getCellData(row).toString()));



    }


}
