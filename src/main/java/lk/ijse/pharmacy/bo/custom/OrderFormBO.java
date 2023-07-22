package lk.ijse.pharmacy.bo.custom;

import lk.ijse.pharmacy.bo.SuperBO;

import java.sql.SQLException;
import java.util.List;

public interface OrderFormBO extends SuperBO {
    public List<String> loadIdsForOrdersForm() throws SQLException, ClassNotFoundException;
    public String getCustNameForOrderForm(String cust_id) throws SQLException, ClassNotFoundException ;
    public  List<String> loadIds() throws SQLException, ClassNotFoundException ;

}
