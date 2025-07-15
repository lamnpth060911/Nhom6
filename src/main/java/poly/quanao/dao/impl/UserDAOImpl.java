package poly.quanao.dao.impl;

import java.util.List;
import poly.quanao.dao.UserDAO;
import poly.quanao.entity.User;
import poly.quanao.util.XJdbc;
import poly.quanao.util.XQuery;

public class UserDAOImpl implements UserDAO {

    String createSql = "INSERT INTO Users(Username, Password, FullName, Role) VALUES(?, ?, ?, ?)";
    String updateSql = "UPDATE Users SET Username=?, Password=?, FullName=?, Role=? WHERE UserId=?";
    String deleteSql = "DELETE FROM Users WHERE UserId=?";
    String findAllSql = "SELECT * FROM Users";
    String findByIdSql = "SELECT * FROM Users WHERE UserId=?";
    String findByUsernameSql = "SELECT * FROM Users WHERE Username=?";
    String findByRoleSql = "SELECT * FROM Users WHERE Role=?";
    String findByKeywordSql = "SELECT * FROM Users WHERE Username LIKE ? OR FullName LIKE ?";

    @Override
    public User create(User entity) {
        Object[] args = {
            entity.getUsername(),
            entity.getPassword(),
            entity.getFullName(),
            entity.getRole()
        };
        XJdbc.executeUpdate(createSql, args);
        return entity;
    }

    @Override
    public void update(User entity) {
        Object[] args = {
            entity.getUsername(),
            entity.getPassword(),
            entity.getFullName(),
            entity.getRole(),
            entity.getUserId()
        };
        XJdbc.executeUpdate(updateSql, args);
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<User> findAll() {
        return XQuery.getBeanList(User.class, findAllSql);
    }

    @Override
    public User findById(Integer id) {
        return XQuery.getSingleBean(User.class, findByIdSql, id);
    }

    @Override
    public User findByUsername(String username) {
        return XQuery.getSingleBean(User.class, findByUsernameSql, username);
    }

    @Override
    public List<User> findByRole(String role) {
        return XQuery.getBeanList(User.class, findByRoleSql, role);
    }

    @Override
    public List<User> findByKeyword(String keyword) {
        String kw = "%" + keyword + "%";
        return XQuery.getBeanList(User.class, findByKeywordSql, kw, kw);
    }
}
