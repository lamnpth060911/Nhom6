package poly.quanao.dao;

import java.util.List;
import poly.quanao.entity.User;

public interface UserDAO extends CrudDAO<User, Integer> {
    User findByUsername(String username);
    List<User> findByRole(String role);
    List<User> findByKeyword(String keyword);
}
