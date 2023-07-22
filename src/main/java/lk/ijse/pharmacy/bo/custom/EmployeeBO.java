package lk.ijse.pharmacy.bo.custom;

import lk.ijse.pharmacy.bo.SuperBO;
import lk.ijse.pharmacy.dto.EmployeeDTO;
import lk.ijse.pharmacy.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException ;
    public boolean saveEmployee(EmployeeDTO empDTO) throws SQLException, ClassNotFoundException ;
    public boolean updateEmployee(EmployeeDTO empDTO) throws SQLException, ClassNotFoundException ;
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<EmployeeDTO> searchEmployee(String id) throws SQLException, ClassNotFoundException ;
}
