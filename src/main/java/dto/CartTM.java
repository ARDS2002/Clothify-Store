package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartTM {

    private Long pID;
    private String pName;
    private Integer pQty;
    private Double pPrice;
    private Double pDiscount;
    private Double total;

}
