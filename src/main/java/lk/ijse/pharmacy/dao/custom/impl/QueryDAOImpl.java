package lk.ijse.pharmacy.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import lk.ijse.pharmacy.dao.custom.QueryDAO;
import lk.ijse.pharmacy.dao.custom.impl.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDAOImpl implements QueryDAO {
    public ObservableList<PieChart.Data> getDataToPieChart() throws SQLException, ClassNotFoundException {
        String sql="SELECT medicine.medName,COUNT(orderdetail.medID) FROM orderDetail INNER JOIN medicine ON medicine.medID = orderdetail.medID INNER JOIN orders\n" +
                " ON orderdetail.orderID=orders.orderID WHERE MONTH(orders.date) = MONTH(CURRENT_DATE()) GROUP BY medicine.medName LIMIT 5;\n";
        ObservableList<PieChart.Data> datalist = FXCollections.observableArrayList();
        ResultSet resultSet = SQLUtil.crudUtil(sql);

        while(resultSet.next()){
            datalist.add(
                    new PieChart.Data(
                            resultSet.getString(1),
                            resultSet.getInt(2)
                    )
            );
        }
        return datalist;

    }
}

