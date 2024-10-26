package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oID;
    private String cName;
    private String cEmail;
    private String paymentType;
    private Double total;
    private Double discount;
    private LocalDate date;
    private Long eID;
    @ManyToMany
    private List<OrderDetailEntity> orderDetails;

}
