package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pID;
    private String pName;
    private String pSize;
    private String pSupplier;
    private Integer pQuantity;
    private Double pBuyingPrice;
    private Double pPrice;
    private Double pDiscount;
    private Double pProfit;
    private String pCategory;
//    @Lob
//    private byte[] pImage;

}
