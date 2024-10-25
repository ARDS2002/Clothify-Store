package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class OrderEntity {

    @Id
    private Long oID;
    private String cName;
    private String cEmail;
    private String paymentType;
    private Double total;
    private Double discount;
    private LocalDate date;
    private Long eID;
    
}
