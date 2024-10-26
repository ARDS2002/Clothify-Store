package service;

import repository.custom.impl.EmployeeDaoImpl;
import repository.custom.impl.ProductDaoImpl;
import service.custom.impl.EmployeeServiceImpl;
import service.custom.impl.OrderServiceImpl;
import service.custom.impl.ProductServiceImpl;
import service.custom.impl.SupplierServiceImpl;
import util.ServiceType;

public class ServiceFactory {
    private static ServiceFactory instance;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance == null ? instance = new ServiceFactory() : instance;
    }

    public <T extends SuperService> T getServiceType(ServiceType type) {

        switch (type){
            case EMPLOYEE : return  (T) new EmployeeServiceImpl();
            case PRODUCT : return (T) new ProductServiceImpl();
            case ORDER : return(T) new OrderServiceImpl();
            case SUPPLIER : return (T) new SupplierServiceImpl();
        }
        return null;
    }
}
