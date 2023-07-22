package lk.ijse.pharmacy.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class ItemDTO {
    private String ItemCode;
    private String ItemMedName;
    private String ItemUnitPrice;
    private String ItemType;
    private String ItemmfgDate;
    private String ItemDate;
    private String ItemQOH;

}
