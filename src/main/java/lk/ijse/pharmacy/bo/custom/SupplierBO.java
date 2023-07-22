package lk.ijse.pharmacy.bo.custom;

import lk.ijse.pharmacy.bo.SuperBO;
import lk.ijse.pharmacy.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    ArrayList<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException;
    boolean saveSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;
    SupplierDTO searchSupplier(String id) throws SQLException, ClassNotFoundException;
}
