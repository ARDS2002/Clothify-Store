package service.custom;

import dto.Product;

import java.util.List;

public interface ProductService {

    boolean addProduct(Product product);

    boolean updateProduct(Product product);

    boolean deleteProduct(Product product);

    Product searchProduct(Long id);

    List<Product> getProduct();

}
