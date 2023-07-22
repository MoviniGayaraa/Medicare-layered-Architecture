package lk.ijse.pharmacy.dao.custom.impl;

import lk.ijse.pharmacy.dao.custom.EmployeeDAO;
import lk.ijse.pharmacy.dao.custom.impl.util.SQLUtil;
import lk.ijse.pharmacy.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {


        ResultSet resultSet = SQLUtil.crudUtil("SELECT * FROM Employee");
        ArrayList<Employee> arrayList = new ArrayList<>();
        while (resultSet.next()) {
            arrayList.add(new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            ));
        }
        return arrayList;
    }

    @Override
    public boolean save(Employee employee) throws SQLException, ClassNotFoundException {
            String sql = "INSERT INTO Employee(empID, firstName, lastName, street, city, lane, contact)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            return SQLUtil.crudUtil(
                    sql,
                    employee.getEmpId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getStreet(),
                    employee.getCity(),
                    employee.getLane(),
                    employee.getContact()
            );

    }

    @Override
    public boolean update(Employee employee) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Employee SET firstName =?, lastName =?, street =?, city =?, " +
                "lane =?, contact =? WHERE empID =?";
        return SQLUtil.crudUtil(
                sql,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getStreet(),
                employee.getCity(),
                employee.getLane(),
                employee.getContact(),
                employee.getEmpId()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Employee WHERE empID = ?";
        return SQLUtil.crudUtil(sql, id);
    }

    @Override
    public ArrayList<Employee> search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Employee WHERE empID=?";

        ArrayList<Employee> arrayList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.crudUtil(sql,id);
        if(resultSet.next()){
            arrayList.add(new Employee(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)));
        }
        return null;
    }

    public List<String> loadIds() throws SQLException, ClassNotFoundException {
        String sql = "SELECT empID FROM employee";
        ResultSet resultSet = SQLUtil.crudUtil(sql);

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    public int getTotEmployee() throws SQLException, ClassNotFoundException {
        String sql="SELECT COUNT(empID) FROM Employee";
        ResultSet resultSet= SQLUtil.crudUtil(sql);
        int count=0;
        while (resultSet.next()){
            count=resultSet.getInt(1);
        }
        return count;

    }
}
