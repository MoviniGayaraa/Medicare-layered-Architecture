package lk.ijse.pharmacy.entity;

import lombok.*;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Order {
    private String orderId;
    private  String custId;
    private  String  empId;
    private LocalDate OrderDate;
    private DecimalFormat orderPayment;
}
