package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {

    private Long oID;
    private String cName;
    private String cEmail;
    private String paymentType;
    private Double total;
    private Double discount;
    private LocalDate date;
    private Long eID;

}
