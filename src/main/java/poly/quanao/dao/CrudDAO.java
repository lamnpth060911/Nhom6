package poly.quanao.dao;

import java.util.List;

public interface CrudDAO<T, ID> {
    T create(T entity);           // Thêm mới
    void update(T entity);        // Cập nhật
    void deleteById(ID id);       // Xoá theo ID
    List<T> findAll();            // Lấy tất cả
    T findById(ID id);            // Tìm theo ID
}
