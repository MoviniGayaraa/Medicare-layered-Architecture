package lk.ijse.pharmacy.dao.custom.impl;

import lk.ijse.pharmacy.dao.custom.CustomerDAO;
import lk.ijse.pharmacy.dao.custom.impl.util.SQLUtil;
import lk.ijse.pharmacy.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.crudUtil("SELECT * FROM customer");
        ArrayList<Customer> arrayList = new ArrayList<>();

        while (resultSet.next()) {
            arrayList.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)));
        }
        return arrayList;
    }

    @Override
    public boolean save(Customer customer) throws SQLException, ClassNotFoundException {
        return SQLUtil.crudUtil("INSERT INTO Customer VALUES (?,?,?,?,?,?,?)",
                customer.getCustID(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getStreet(),
                customer.getCity(),
                customer.getLane(),
                customer.getContact()
        );
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE customer SET firstName=?,lastName=?,street=?,city=?,lane=?,contact=? WHERE custID=?";
        return SQLUtil.crudUtil(sql, customer.getFirstName(), customer.getLastName(), customer.getStreet(),
                customer.getCity(),
                customer.getLane(),
                customer.getContact(),
                customer.getCustID()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM customer WHERE custID=?";
        return SQLUtil.crudUtil(sql,id);
    }

    @Override
    public ArrayList<Customer> search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }


    public List<String> loadIds() throws SQLException, ClassNotFoundException {
        String sql = "SELECT custID FROM customer";
        ResultSet resultSet = SQLUtil.crudUtil(sql);

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    public String getCustName(String cust_id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT firstName,lastName FROM customer WHERE custID=?";
        ResultSet resultSet = SQLUtil.crudUtil(sql, cust_id);

        if (resultSet.next()) {
            return (new String(
                    resultSet.getString(1) + " " + resultSet.getString(2)
            ));
        }
        return null;
    }

    public int getTotCustomers() throws SQLException, ClassNotFoundException {
        String sql="SELECT COUNT(custID) FROM Customer";
        ResultSet resultSet= SQLUtil.crudUtil(sql);
        int count=0;
        while (resultSet.next()){
            count=resultSet.getInt(1);
        }
        return count;

    }
}
