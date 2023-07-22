package lk.ijse.pharmacy.dao.custom.impl;

import lk.ijse.pharmacy.dao.custom.OrderDeatailDAO;
import lk.ijse.pharmacy.dao.custom.impl.util.SQLUtil;
import lk.ijse.pharmacy.dto.PlaceOrderDTO;
import lk.ijse.pharmacy.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDeatailDAOImpl implements OrderDeatailDAO {
    public static boolean save(String orderid, List<PlaceOrderDTO> placeOrderDTOList) throws SQLException, ClassNotFoundException {
        for(PlaceOrderDTO placeOrderDTO : placeOrderDTOList) {
            if(!save(orderid, placeOrderDTO)) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(String orderid, PlaceOrderDTO placeOrderDTO) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO orderdetail(medID,orderID,qty)" +
                "VALUES(?, ?, ?)";

        return SQLUtil.crudUtil(
                sql,
                placeOrderDTO.getOrdereditemcode(),
                orderid,
                placeOrderDTO.getOrdereditemqty()
        );
    }

    @Override
    public ArrayList<OrderDetails> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDetails dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetails dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<OrderDetails> search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
