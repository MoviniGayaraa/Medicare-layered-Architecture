package lk.ijse.pharmacy.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BillDTO {

    private String billID;
    private String orderID;
    private double totalAmt;
    private double custPay;
    private double discount;
    private Date  orderDate;

}
