package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
