package lk.ijse.pharmacy.bo.custom;

import lk.ijse.pharmacy.bo.SuperBO;
import lk.ijse.pharmacy.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LoginUserBO extends SuperBO {
    public ArrayList<EmployeeDTO> searchUser(String id) throws SQLException, ClassNotFoundException ;
}
