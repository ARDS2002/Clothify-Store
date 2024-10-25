package service.custom.impl;

import dto.Product;
import entity.ProductEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.ProductDao;
import service.custom.ProductService;
import util.DaoType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    @Override
    public boolean addProduct(Product product) {
        ProductEntity entity = new ModelMapper().map(product, ProductEntity.class);

        ProductDao productDao = DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);

        try {
            return productDao.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateProduct(Product product) {
        ProductEntity entity = new ModelMapper().map(product, ProductEntity.class);

        ProductDao productDao = DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);

        try {
            return productDao.update(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteProduct(Product product) {
        ProductEntity entity = new ModelMapper().map(product, ProductEntity.class);
        ProductDao productDao = DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);

        try {
            return productDao.delete(entity.getPID());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Product searchProduct(Long id) {
        ProductDao productDao = DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);
        ProductEntity entity = productDao.search(id);

        if (entity == null) {
            return null;
        }

        return new ModelMapper().map(entity, Product.class);
    }

    @Override
    public List<Product> getProduct() {
        ProductDao productDao = DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);
        List<ProductEntity> entityList = productDao.getAll();

        return (entityList != null) ?
                entityList.stream()
                        .map(entity -> new ModelMapper().map(entity, Product.class))
                        .collect(Collectors.toList())
                : new ArrayList<>();
    }

}
