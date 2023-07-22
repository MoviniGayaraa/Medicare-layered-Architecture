package lk.ijse.pharmacy.bo;

import lk.ijse.pharmacy.bo.custom.impl.CustomerBOImpl;
import lk.ijse.pharmacy.bo.custom.impl.DashbordBOImpl;
import lk.ijse.pharmacy.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.pharmacy.bo.custom.impl.OrderFormBOImpl;
import lk.ijse.pharmacy.dao.DAOFactory;
import lk.ijse.pharmacy.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.pharmacy.dao.custom.impl.EmployeeDAOImpl;

public class BOFactory {
    private static BOFactory boObjectCreater;

    private BOFactory() {

    }

    public static BOFactory getBOFactory() {
        if (boObjectCreater==null) {
            boObjectCreater = new BOFactory();
        }
        return boObjectCreater;
    }

    public enum BOType{
        CUSTOMER,DASHBORD,EMPLOYEE,ORDERFORM
    }

    public <T extends SuperBO>T getBO(BOType rest) {
        switch (rest) {
            case CUSTOMER:
                return (T) new CustomerBOImpl();
            case DASHBORD:
                return (T) new DashbordBOImpl();
            case EMPLOYEE:
                return (T) new EmployeeBOImpl();
            case ORDERFORM:
                return (T) new OrderFormBOImpl();

            default:
                return null;

        }
    }
}
