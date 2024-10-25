package repository;

import java.util.List;

public interface CrudDao<T> extends SuperDao {

    boolean save(T t);

    boolean delete(Long id);

    List<T> getAll();

    boolean update(T t);

    T search(Long id);

}
