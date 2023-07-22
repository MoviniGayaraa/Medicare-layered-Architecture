package lk.ijse.pharmacy.bo.custom;

import lk.ijse.pharmacy.bo.SuperBO;
import lk.ijse.pharmacy.dto.EmployeeDTO;

import java.sql.SQLException;

public interface SignUpBO extends SuperBO {
    public boolean saveSignUp(EmployeeDTO empDTO) throws SQLException, ClassNotFoundException ;
}
