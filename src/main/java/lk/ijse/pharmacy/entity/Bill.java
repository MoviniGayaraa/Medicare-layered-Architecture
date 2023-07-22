package lk.ijse.pharmacy.entity;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Bill {

        private String billID;
        private String orderID;
        private double totalAmt;
        private double custPay;
        private double discount;
        private Date orderDate;

}
