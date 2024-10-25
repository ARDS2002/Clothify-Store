package dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {

    private Long eID;
    private String eName;
    private String eContact;
    private String eEmail;
    private String eAddress;
    private String ePassword;

}
