package lk.ijse.pharmacy.bo.custom.impl;

import lk.ijse.pharmacy.dao.DAOFactory;
import lk.ijse.pharmacy.dao.custom.UserDAO;
import lk.ijse.pharmacy.dao.custom.impl.UserDAOImpl;
import lk.ijse.pharmacy.dao.custom.impl.util.SQLUtil;
import lk.ijse.pharmacy.dto.EmployeeDTO;
import lk.ijse.pharmacy.dto.UserDTO;
import lk.ijse.pharmacy.entity.Employee;
import lk.ijse.pharmacy.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginUserBOImpl {
    UserDAOImpl userDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USER);

    public ArrayList<UserDTO> searchName(String name) throws SQLException, ClassNotFoundException {
        ArrayList<User> arrayList=  userDAO.search(name);

        ArrayList<UserDTO> dtoArrayList = new ArrayList<>();
        for (User c : arrayList) {
            dtoArrayList.add(new UserDTO(c.getUsername(),c.getPassword(),c.getEmial()));
        }
        return dtoArrayList;
    }
}
