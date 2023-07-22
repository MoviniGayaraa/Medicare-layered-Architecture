package lk.ijse.pharmacy.bo.custom.impl;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import lk.ijse.pharmacy.dao.DAOFactory;
import lk.ijse.pharmacy.dao.custom.CustomerDAO;
import lk.ijse.pharmacy.dao.custom.QueryDAO;
import lk.ijse.pharmacy.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.pharmacy.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.pharmacy.dao.custom.impl.OrdersDAOImpl;
import lk.ijse.pharmacy.dao.custom.impl.QueryDAOImpl;

import java.sql.SQLException;

public class DashbordBOImpl {

    OrdersDAOImpl ordersDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    CustomerDAO customerDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    EmployeeDAOImpl employeeDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    QueryDAOImpl queryDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    public int getTotCustomers() throws SQLException, ClassNotFoundException {
        return customerDAO.getTotCustomers();
    }

    public int getTotEmployee() throws SQLException, ClassNotFoundException{
        return employeeDAO.getTotEmployee();
    }

    public int getTotalSalesOrder() throws SQLException, ClassNotFoundException {
        return ordersDAO.getTotalSales();
    }

    public XYChart.Series lineChartData() throws SQLException, ClassNotFoundException {
       return ordersDAO.lineChartData();
    }

    public ObservableList<PieChart.Data> getDataToPieChart() throws SQLException, ClassNotFoundException {
        return queryDAO.getDataToPieChart();
    }


}
