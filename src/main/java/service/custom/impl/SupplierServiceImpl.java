package service.custom.impl;

import dto.Supplier;
import entity.SupplierEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.SupplierDao;
import service.custom.SupplierService;
import util.DaoType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SupplierServiceImpl implements SupplierService {
    private static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public boolean addSupplier(Supplier supplier) {
        try {
            SupplierEntity entity = modelMapper.map(supplier, SupplierEntity.class);
            SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
            return supplierDao.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteSupplier(Supplier supplier) {
        try {
            SupplierEntity entity = modelMapper.map(supplier, SupplierEntity.class);
            SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
            return supplierDao.delete(entity.getSID());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        try {
            SupplierEntity entity = modelMapper.map(supplier, SupplierEntity.class);
            SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
            return supplierDao.update(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Supplier searchSupplier(Long id) {
        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
        SupplierEntity entity = supplierDao.search(id);
        return entity != null ? modelMapper.map(entity, Supplier.class) : null;
    }

    @Override
    public List<Supplier> getSupplier() {
        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
        List<SupplierEntity> entityList = supplierDao.getAll();

        return (entityList != null) ?
                entityList.stream()
                        .map(entity -> new ModelMapper().map(entity, Supplier.class))
                        .collect(Collectors.toList())
                : new ArrayList<>();
    }

}
