package lk.ijse.pharmacy.dao;

import lk.ijse.pharmacy.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoObjectCreater;

    private DAOFactory() {

    }

    public static DAOFactory getDAOFactory() {
        if (daoObjectCreater == null) {
            daoObjectCreater = new DAOFactory();
        }
        return daoObjectCreater;
    }

    public enum DAOTypes {
        BILL,CUSTOMER, EMPLOYEE,ITEM,ORDER_DETAIL,ORDER,SUPPLIER ,USER,QUERY
    }

    public <T extends SuperDAO> T getDAO(DAOTypes res) {
        switch (res) {
            case BILL:
                return (T) new BillDAOImpl();
            case CUSTOMER:
                return (T) new CustomerDAOImpl();
            case EMPLOYEE:
                return (T) new EmployeeDAOImpl();
            case ITEM:
                return (T) new ItemDAOImpl();
            case ORDER_DETAIL:
                return (T) new OrderDeatailDAOImpl();
            case ORDER:
                return (T) new OrdersDAOImpl();
            case SUPPLIER:
                return (T) new SupplierDAOImpl();
            case USER:
                return (T) new UserDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();

            default:
                return null;
        }

    }
}