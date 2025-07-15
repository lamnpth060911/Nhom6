/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.quanao.dao.impl;

import java.util.List;
import poly.quanao.dao.UserDAO;
import poly.quanao.entity.User;
import poly.quanao.util.XJdbc;
import poly.quanao.util.XQuery;

/**
 *
 * @author LENOVO
 */
public class UserDAOImpl implements UserDAO{
    String createSql = "INSERT INTO Users(Username,Password, Enabled ,Fullname, Photo,Manager) VALUES(?, ?, ?, ?, ?, ?)";
    String updateSql = "UPDATE Users SET Password=?, Enabled=?, Fullname=?, Photo=?, Manager=? where Username=?";
    String deleteSql = "DELETE FROM Users where Username=?";
    String findAllSql = "SELECT * FROM Users";
    String findByIdSql = "SELECT * FROM Users where Username=?";
    @Override
    public User create(User entity) {
        Object[] values = {
        entity.getUsername(),
        entity.getPassword(),
        entity.isEnabled(),
        entity.getFullname(),
        entity.getPhoto(),
        entity.isManager()
        };
        XJdbc.executeUpdate(createSql, values);  
        return entity;
    }

    @Override
    public void update(User entity) {
       Object[] values = {    
        entity.getPassword(),
        entity.isEnabled(),
        entity.getFullname(),
        entity.getPhoto(),
        entity.isManager(),
        entity.getUsername()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(String username) {
        XJdbc.executeUpdate(deleteSql, username);
    }

    @Override
    public List<User> findAll() {
        return XQuery.getBeanList(User.class, findAllSql);
    }

    @Override
    public User findById(String username) {
        return XQuery.getSingleBean(User.class, findByIdSql, username);
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
