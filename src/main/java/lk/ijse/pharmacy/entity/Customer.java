package lk.ijse.pharmacy.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Customer {
    private String custID;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String lane;
    private String contact;

}
