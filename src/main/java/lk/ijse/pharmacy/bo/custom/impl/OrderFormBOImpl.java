package lk.ijse.pharmacy.bo.custom.impl;

import lk.ijse.pharmacy.dao.DAOFactory;
import lk.ijse.pharmacy.dao.custom.CustomerDAO;
import lk.ijse.pharmacy.dao.custom.EmployeeDAO;
import lk.ijse.pharmacy.dao.custom.impl.ItemDAOImpl;
import lk.ijse.pharmacy.dao.custom.impl.OrderDeatailDAOImpl;
import lk.ijse.pharmacy.dao.custom.impl.OrdersDAOImpl;
import lk.ijse.pharmacy.dao.custom.impl.util.SQLUtil;
import lk.ijse.pharmacy.db.DBConnection;
import lk.ijse.pharmacy.dto.ItemDTO;
import lk.ijse.pharmacy.dto.PlaceOrderDTO;
import lk.ijse.pharmacy.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderFormBOImpl {
    CustomerDAO customerDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    EmployeeDAO employeeDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    OrdersDAOImpl ordersDAO = new OrdersDAOImpl();
    OrderDeatailDAOImpl deatailDAO = new OrderDeatailDAOImpl();
    ItemDAOImpl itemDAO = new ItemDAOImpl();

    public  List<String> loadIdsForOrdersForm() throws SQLException, ClassNotFoundException {
        return customerDAO.loadIds();
    }

    public String getCustNameForOrderForm(String cust_id) throws SQLException, ClassNotFoundException {
        return customerDAO.getCustName(cust_id);
    }

    public  List<String> loadIds() throws SQLException, ClassNotFoundException {
        return employeeDAO.loadIds();
    }

    public List<String> loadCodes() throws SQLException, ClassNotFoundException {
        return itemDAO.loadCodes();
    }

    public ArrayList<ItemDTO> findById(String itemcode) throws SQLException, ClassNotFoundException {
        ArrayList<Item> arrayList=  itemDAO.search(itemcode);

        ArrayList<ItemDTO> dtoArrayList = new ArrayList<>();
        for (Item c : arrayList) {
            dtoArrayList.add(new ItemDTO(c.getItemCode(),c.getItemMedName(),c.getItemUnitPrice(),c.getItemType(),c.getItemmfgDate(),c.getItemDate(),c.getItemQOH()));
        }
        return dtoArrayList;


    }

    public String getNextOrderId() throws SQLException, ClassNotFoundException {
        return ordersDAO.getNextOrderId();
    }

    public boolean placeOrder(String orderid, String custid, String ordpay, String empid, List<PlaceOrderDTO> placeOrderDTOList) throws SQLException, ClassNotFoundException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isSaved = save(orderid, custid, LocalDate.now(), ordpay, empid);
            if (isSaved) {
                boolean isUpdated = ItemDAOImpl.updateQty(placeOrderDTOList);
                if (isUpdated) {
                    boolean isOrdered = deatailDAO.save(orderid, placeOrderDTOList);
                    if (isOrdered) {
                        con.commit();
                        return true;
                    }
                }
            }
            return false;
        } catch (SQLException er) {
            System.out.println(er);
            con.rollback();
            return false;
        } finally {
            System.out.println("finally");
            con.setAutoCommit(true);
        }
    }

    private static boolean save(String orderid, String custid, LocalDate now, String ordpay, String empid) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO orders(orderID,custID,empID,date,orderPayment) VALUES(?,?,?,?,?)";

        return SQLUtil.crudUtil(
                sql,
                orderid,
                custid,
                empid,
                now,
                ordpay
        );
    }
}
