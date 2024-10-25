package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class EmployeeEntity {

    @Id
    private Long eID;
    private String eName;
    private String eContact;
    private String eEmail;
    private String eAddress;
    private String ePassword;

}
