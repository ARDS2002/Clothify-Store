package dto;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

    private Long pID;
    private String pName;
    private String pSize;
    private String pSupplier;
    private Integer pQuantity;
    private Double pPrice;
    private Double pDiscount;
    private String pCategory;
    @Lob
    private byte[] pImage;

}
