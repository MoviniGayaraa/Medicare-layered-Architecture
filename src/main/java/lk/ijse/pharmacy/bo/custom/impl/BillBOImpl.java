package lk.ijse.pharmacy.bo.custom.impl;

import lk.ijse.pharmacy.dao.DAOFactory;
import lk.ijse.pharmacy.dao.custom.BillDAO;
import lk.ijse.pharmacy.dao.custom.impl.BillDAOImpl;
import lk.ijse.pharmacy.dao.custom.impl.OrdersDAOImpl;
import lk.ijse.pharmacy.dao.custom.impl.util.SQLUtil;
import lk.ijse.pharmacy.dto.BillDTO;
import lk.ijse.pharmacy.dto.CustomerDTO;
import lk.ijse.pharmacy.entity.Bill;
import lk.ijse.pharmacy.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillBOImpl  {
    OrdersDAOImpl ordersDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    BillDAO billDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.BILL);

    public List<String> LoadOrderIds() throws SQLException, ClassNotFoundException {
        return ordersDAO.LoadOrderIds();
    }

    public ArrayList<BillDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Bill> arrayList =  billDAO.getAll();
        ArrayList<BillDTO> dtoArrayList = new ArrayList<>();
        for (Bill c : arrayList) {
            dtoArrayList.add(new BillDTO(c.getBillID(),c.getOrderID(),c.getTotalAmt(),c.getCustPay(),c.getDiscount(),c.getOrderDate()));
        }
        return dtoArrayList;
    }

    public boolean save(BillDTO c) throws SQLException, ClassNotFoundException {
        return billDAO.save(new Bill(c.getBillID(),c.getOrderID(),c.getTotalAmt(),c.getCustPay(),c.getDiscount(),c.getOrderDate()));
    }

    public boolean update(BillDTO c) throws SQLException, ClassNotFoundException {
        return billDAO.update(new Bill(c.getBillID(),c.getOrderID(),c.getTotalAmt(),c.getCustPay(),c.getDiscount(),c.getOrderDate()));

    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
       return billDAO.delete(id);
    }

    public ArrayList<BillDTO> search(String id) throws SQLException, ClassNotFoundException {
        ArrayList<Bill> arrayList =  billDAO.search(id);
        ArrayList<BillDTO> dtoArrayList = new ArrayList<>();
        for (Bill c : arrayList) {
            dtoArrayList.add(new BillDTO(c.getBillID(),c.getOrderID(),c.getTotalAmt(),c.getCustPay(),c.getDiscount(),c.getOrderDate()));
        }
        return dtoArrayList;
    }
}
