package lk.ijse.pharmacy.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PlaceOrderDTO {
    private String ordereditemcode;
    private Integer ordereditemqty;
}
