package service.custom;

import dto.Supplier;
import service.SuperService;

import java.util.List;

public interface SupplierService extends SuperService {

    boolean addSupplier(Supplier supplier);

    boolean deleteSupplier(Supplier supplier);

    boolean updateSupplier(Supplier supplier);

    Supplier searchSupplier(Long id);

    List<Supplier> getSupplier();

}
