package lk.ijse.pharmacy.bo.custom;

import lk.ijse.pharmacy.bo.SuperBO;

import java.sql.SQLException;

public interface DashbordBO extends SuperBO {
    public int getTotCustomers() throws SQLException, ClassNotFoundException ;
    public int getTotEmployee() throws SQLException, ClassNotFoundException;
}
