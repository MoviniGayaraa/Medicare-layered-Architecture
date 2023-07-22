package lk.ijse.pharmacy.bo.custom.impl;

import lk.ijse.pharmacy.dao.DAOFactory;
import lk.ijse.pharmacy.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.pharmacy.dao.custom.impl.util.SQLUtil;
import lk.ijse.pharmacy.dto.EmployeeDTO;
import lk.ijse.pharmacy.dto.ItemDTO;
import lk.ijse.pharmacy.dto.SupplierDTO;
import lk.ijse.pharmacy.entity.Employee;
import lk.ijse.pharmacy.entity.Item;
import lk.ijse.pharmacy.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl {
    SupplierDAOImpl supplierDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    public ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> arrayList =  supplierDAO.getAll();
        ArrayList<SupplierDTO> dtoArrayList = new ArrayList<>();
        for (Supplier c : arrayList) {
            dtoArrayList.add(new SupplierDTO(c.getSupID(),c.getFirstName(),c.getLastName(),c.getStreet(),
                    c.getCity(),c.getLane(),c.getContact()));
        }
        return dtoArrayList;
    }


    public boolean saveSupplier(SupplierDTO c) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(c.getSupID(),c.getFirstName(),c.getLastName(),c.getStreet(),
                c.getCity(),c.getLane(),c.getContact()));

    }


    public boolean updateSupplier(SupplierDTO c) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(c.getSupID(),c.getFirstName(),c.getLastName(),c.getStreet(),
                c.getCity(),c.getLane(),c.getContact()));
    }


    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }


    public ArrayList<SupplierDTO> searchSupplier(String id) throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> arrayList=  supplierDAO.search(id);

        ArrayList<SupplierDTO> dtoArrayList = new ArrayList<>();
        for (Supplier c : arrayList) {
            dtoArrayList.add(new SupplierDTO(c.getSupID(),c.getFirstName(),c.getLastName(),c.getStreet(),c.getCity(),c.getLane(),c.getContact()));
        }
        return dtoArrayList;

    }
}
