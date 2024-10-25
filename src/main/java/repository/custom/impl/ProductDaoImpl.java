package repository.custom.impl;

import entity.ProductEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.custom.ProductDao;
import util.HibernateUtil;

import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Override
    public boolean save(ProductEntity productEntity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(productEntity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(Long id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            ProductEntity product = session.get(ProductEntity.class, id);
            if (product != null) {
                session.remove(product);
                transaction.commit();
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public List<ProductEntity> getAll() {
        Session session = HibernateUtil.getSession();
        List<ProductEntity> products = null;
        try {
            session.beginTransaction();
            products = session.createQuery("from ProductEntity", ProductEntity.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return products;
    }

    @Override
    public boolean update(ProductEntity productEntity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(productEntity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public ProductEntity search(Long id) {
        Session session = HibernateUtil.getSession();
        try {
            return session.get(ProductEntity.class, id);
        } finally {
            session.close();
        }
    }
}
