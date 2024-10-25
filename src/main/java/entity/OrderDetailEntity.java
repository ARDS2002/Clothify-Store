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
public class OrderDetailEntity {

    @Id
    private Long pID;
    @Id
    private Long oID;
    private String pName;
    private int quantity;
    private double pPrice;
    private double discount;

}
