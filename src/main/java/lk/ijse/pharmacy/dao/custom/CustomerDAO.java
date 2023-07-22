package lk.ijse.pharmacy.dao.custom;

import lk.ijse.pharmacy.dao.CrudDAO;
import lk.ijse.pharmacy.dao.custom.impl.util.SQLUtil;
import lk.ijse.pharmacy.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer,String> {
    public List<String> loadIds() throws SQLException, ClassNotFoundException;

    public String getCustName(String cust_id) throws SQLException, ClassNotFoundException ;

    public int getTotCustomers() throws SQLException, ClassNotFoundException ;
}
