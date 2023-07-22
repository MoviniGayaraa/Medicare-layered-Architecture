package lk.ijse.pharmacy.bo.custom.impl;

import lk.ijse.pharmacy.dao.DAOFactory;
import lk.ijse.pharmacy.dao.custom.EmployeeDAO;
import lk.ijse.pharmacy.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.pharmacy.dao.custom.impl.util.SQLUtil;
import lk.ijse.pharmacy.dto.CustomerDTO;
import lk.ijse.pharmacy.dto.EmployeeDTO;
import lk.ijse.pharmacy.entity.Customer;
import lk.ijse.pharmacy.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl {
    EmployeeDAO employeeDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> arrayList =  employeeDAO.getAll();
        ArrayList<EmployeeDTO> dtoArrayList = new ArrayList<>();
        for (Employee c : arrayList) {
            dtoArrayList.add(new EmployeeDTO(c.getEmpId(),c.getFirstName(),c.getFirstName(),c.getStreet(),c.getCity(),c.getLane(),c.getContact()));
        }
        return dtoArrayList;

    }

    public boolean saveEmployee(EmployeeDTO empDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(empDTO.getEmpId(), empDTO.getFirstName(), empDTO.getLastName(), empDTO.getStreet(), empDTO.getCity(), empDTO.getLane(), empDTO.getContact()));

    }

    public boolean updateEmployee(EmployeeDTO empDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(empDTO.getEmpId(), empDTO.getFirstName(), empDTO.getLastName(), empDTO.getStreet(), empDTO.getCity(), empDTO.getLane(), empDTO.getContact()));

    }


    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }


    public ArrayList<EmployeeDTO> searchEmployee(String id) throws SQLException, ClassNotFoundException {
        ArrayList<Employee> arrayList=  employeeDAO.search(id);

        ArrayList<EmployeeDTO> dtoArrayList = new ArrayList<>();
        for (Employee c : arrayList) {
            dtoArrayList.add(new EmployeeDTO(c.getEmpId(),c.getFirstName(),c.getFirstName(),c.getStreet(),c.getCity(),c.getLane(),c.getContact()));
        }
        return dtoArrayList;

    }
}
