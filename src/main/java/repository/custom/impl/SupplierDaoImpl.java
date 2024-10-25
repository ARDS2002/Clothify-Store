package repository.custom.impl;

import entity.SupplierEntity;
import repository.custom.SupplierDao;

import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public boolean save(SupplierEntity supplierEntity) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<SupplierEntity> getAll() {
        return List.of();
    }

    @Override
    public boolean update(SupplierEntity supplierEntity) {
        return false;
    }

    @Override
    public SupplierEntity search(Long id) {
        return null;
    }
}
