package repository.custom.impl;

import entity.OrderEntity;
import repository.custom.OrderDao;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean save(OrderEntity orderEntity) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<OrderEntity> getAll() {
        return List.of();
    }

    @Override
    public boolean update(OrderEntity orderEntity) {
        return false;
    }

    @Override
    public OrderEntity search(Long id) {
        return null;
    }
}
