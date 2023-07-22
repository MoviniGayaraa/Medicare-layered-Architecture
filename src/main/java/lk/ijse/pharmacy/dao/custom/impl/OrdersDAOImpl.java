package lk.ijse.pharmacy.dao.custom.impl;

import javafx.scene.chart.XYChart;
import lk.ijse.pharmacy.dao.custom.OrdersDAO;
import lk.ijse.pharmacy.dao.custom.impl.util.SQLUtil;
import lk.ijse.pharmacy.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAOImpl implements OrdersDAO {

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Order dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Order dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Order> search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public String getNextOrderId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT orderID FROM orders ORDER BY orderID DESC LIMIT 1";

        ResultSet resultSet = SQLUtil.crudUtil(sql);

        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private String splitOrderId(String currentId) {
        if (currentId != null) {
            String[] strings = currentId.split("ORD-");
            int id = Integer.parseInt(strings[1]);
            ++id;
            String digit = String.format("%03d", id);
            return "ORD-" + digit;
        }
        return "ORD-001";
    }

    public int getTotalSales() throws SQLException, ClassNotFoundException {
        String sql="SELECT count(orderID) FROM orders WHERE date =curdate()";
        ResultSet resultSet= SQLUtil.crudUtil(sql);
        int count=0;
        while (resultSet.next()){
            count=resultSet.getInt(1);
        }
        return count;
    }

    public List<String> LoadOrderIds() throws SQLException, ClassNotFoundException {

        String sql = "SELECT orderID FROM orders";
        List<String> allItemData = new ArrayList<>();

        ResultSet resultSet = SQLUtil.crudUtil(sql);
        while (resultSet.next()) {
            allItemData.add(resultSet.getString(1));


        }
        return allItemData;
    }

    public XYChart.Series lineChartData() throws SQLException, ClassNotFoundException {
        String sql="SELECT MONTHNAME(date),sum(orderPayment) from orders group by MONTHNAME(date)";
        ResultSet resultSet= SQLUtil.crudUtil(sql);
        XYChart.Series series=new XYChart.Series();
        while (resultSet.next()){
            series.getData().add(new XYChart.Data(resultSet.getString(1),resultSet.getInt(2)));
        }
        return series;

    }


}
