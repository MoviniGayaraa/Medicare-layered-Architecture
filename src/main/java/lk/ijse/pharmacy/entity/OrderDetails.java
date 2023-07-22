package lk.ijse.pharmacy.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class OrderDetails {
    private String  medId;
    private String  orderId;
    private Integer qty;
}
