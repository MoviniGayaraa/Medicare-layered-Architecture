package lk.ijse.pharmacy.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.pharmacy.dao.custom.BillDAO;
import lk.ijse.pharmacy.dao.custom.impl.util.SQLUtil;
import lk.ijse.pharmacy.dto.BillDTO;
import lk.ijse.pharmacy.entity.Bill;
import lk.ijse.pharmacy.tm.BillTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BillDAOImpl implements BillDAO {
    @Override
    public ArrayList<Bill> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM bill";

        ArrayList<Bill> obList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.crudUtil(sql);

        while (resultSet.next()) {

            obList.add(new Bill(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5),
                    resultSet.getDate(6)
            ));

        }
        return obList;
    }

    @Override
    public boolean save(Bill bill) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO bill(billID,orderID,totalAmt,custPay,discount,orderDate)" +
                "VALUES (?,?,?,?,?,?)";

        return SQLUtil.crudUtil(
                sql,
                bill.getBillID(),
                bill.getOrderID(),
                bill.getTotalAmt(),
                bill.getCustPay(),
                bill.getDiscount(),
                bill.getOrderDate()
        );
    }

    @Override
    public boolean update(Bill bill) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE bill SET orderID=?,totalAmt=?,custPay=?,discount=?,orderDate=? WHERE billID=?";

        return SQLUtil.crudUtil(
                sql,
                bill.getOrderID(),
                bill.getTotalAmt(),
                bill.getCustPay(),
                bill.getDiscount(),
                bill.getOrderDate(),
                bill.getBillID()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM bill WHERE billID=?";
        return SQLUtil.crudUtil(
                sql,
                id);
    }

    @Override
    public ArrayList<Bill> search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM bill WHERE billID=?";

        ResultSet resultSet = SQLUtil.crudUtil(sql,id);
        ArrayList<Bill> arrayList = new ArrayList<>();
        if (resultSet.next()) {
            arrayList.add(new Bill(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5),
                    resultSet.getDate(6)));
        }

        return arrayList;

    }
}
