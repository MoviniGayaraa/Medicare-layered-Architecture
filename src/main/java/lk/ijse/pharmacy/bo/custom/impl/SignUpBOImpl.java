package lk.ijse.pharmacy.bo.custom.impl;

import lk.ijse.pharmacy.dao.DAOFactory;
import lk.ijse.pharmacy.dao.custom.impl.UserDAOImpl;
import lk.ijse.pharmacy.dto.UserDTO;
import lk.ijse.pharmacy.entity.User;

import java.sql.SQLException;

public class SignUpBOImpl {
    UserDAOImpl userDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USER);

    public boolean save(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return userDAO.save(new User(userDTO.getUsername(),userDTO.getPassword(),userDTO.getEmial()));
    }

}
