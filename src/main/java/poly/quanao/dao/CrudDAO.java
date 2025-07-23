package poly.quanao.dao;

import java.util.List;

public interface CrudDAO<T, K> {   // K = kiểu khóa chính
    void create(T entity);
    void update(T entity);
    void deleteById(K id);
    T findById(K id);
    List<T> findAll();
}
