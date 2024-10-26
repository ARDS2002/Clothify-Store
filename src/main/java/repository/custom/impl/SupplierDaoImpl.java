package repository.custom.impl;

import entity.SupplierEntity;
import org.hibernate.Session;
import repository.custom.SupplierDao;
import util.HibernateUtil;

import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public boolean save(SupplierEntity supplier) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.persist(supplier);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(Long id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            SupplierEntity supplier = session.get(SupplierEntity.class, id);
            if (supplier != null) {
                session.remove(supplier);
                session.getTransaction().commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public List<SupplierEntity> getAll() {
        Session session = HibernateUtil.getSession();
        try {
            return session.createQuery("from SupplierEntity", SupplierEntity.class).list();
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(SupplierEntity supplierentity) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.merge(supplierentity);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public SupplierEntity search(Long id) {
        Session session = HibernateUtil.getSession();
        try {
            return session.get(SupplierEntity.class, id);
        } finally {
            session.close();
        }
    }

}
