package repository;

import repository.custom.impl.EmployeeDaoImpl;
import repository.custom.impl.OrderDaoImpl;
import repository.custom.impl.ProductDaoImpl;
import repository.custom.impl.SupplierDaoImpl;
import util.DaoType;

public class DaoFactory {

    private static DaoFactory instance;

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return instance == null ? instance = new DaoFactory() : instance;
    }

    public <T extends SuperDao> T getDaoType(DaoType type) {
        return switch (type) {
            case EMPLOYEE -> (T) new EmployeeDaoImpl();
            case PRODUCT -> (T) new ProductDaoImpl();
            case ORDER -> (T) new OrderDaoImpl();
            case SUPPLIER -> (T) new SupplierDaoImpl();
        };

    }
}
