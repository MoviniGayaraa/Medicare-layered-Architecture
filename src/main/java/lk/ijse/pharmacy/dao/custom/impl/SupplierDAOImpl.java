package lk.ijse.pharmacy.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.pharmacy.dao.custom.SupplierDAO;
import lk.ijse.pharmacy.dao.custom.impl.util.SQLUtil;
import lk.ijse.pharmacy.dto.SupplierDTO;
import lk.ijse.pharmacy.entity.Supplier;
import lk.ijse.pharmacy.tm.SupplierTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> obList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.crudUtil("SELECT * FROM supplier");
        while(resultSet.next()){
            obList.add(new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)

            ));
        }
        return obList;
    }

    @Override
    public boolean save(Supplier supplier) throws SQLException, ClassNotFoundException {
        return SQLUtil.crudUtil("INSERT INTO supplier VALUES (?,?,?,?,?,?,?)",
                supplier.getSupID(),
                supplier.getFirstName(),
                supplier.getLastName(),
                supplier.getStreet(),
                supplier.getCity(),
                supplier.getLane(),
                supplier.getContact()
        );
    }

    @Override
    public boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE supplier SET firstName=?,lastName=?,street=?,city=?,lane=?,contact=? WHERE supID=?";
        return SQLUtil.crudUtil(
                sql,
                supplier.getFirstName(),
                supplier.getLastName(),
                supplier.getStreet(),
                supplier.getCity(),
                supplier.getLane(),
                supplier.getContact(),
                supplier.getSupID()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.crudUtil( "DELETE FROM supplier WHERE supID=?", id);
    }

    @Override
    public ArrayList<Supplier> search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM supplier WHERE supID=?";

        ArrayList<Supplier> obList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.crudUtil(sql,id);
        if(resultSet.next()){
             obList.add(new Supplier(
                     resultSet.getString(1),
                     resultSet.getString(2),
                     resultSet.getString(3),
                     resultSet.getString(4),
                     resultSet.getString(5),
                     resultSet.getString(6),
                     resultSet.getString(7)));
        }
        return null;
    }
}
