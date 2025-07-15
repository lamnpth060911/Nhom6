package poly.quanao.dao.impl;

import java.util.List;
import poly.quanao.dao.UserDAO;
import poly.quanao.entity.User;



public class UserDAOImpl implements UserDAO {

    String createSql = "INSERT INTO Users (Username, Password, FullName, Role) VALUES (?, ?, ?, ?)";
    String updateSql = "UPDATE Users SET Username=?, Password=?, FullName=?, Role=? WHERE UserId=?";
    String deleteSql = "DELETE FROM Users WHERE UserId=?";
    String findAllSql = "SELECT * FROM Users";
    String findByIdSql = "SELECT * FROM Users WHERE UserId=?";




}
