/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.quanao.dao.impl;

import java.util.List;
import poly.quanao.dao.CategoryDAO;
import poly.quanao.entity.Category;
import poly.quanao.util.XJdbc;
import poly.quanao.util.XQuery;

/**
 *
 * @author LENOVO
 */
public class CategoryDAOImpl implements CategoryDAO{
    String createSql = "INSERT INTO Categories (CategoryId, CategoryName) VALUES(?, ?)";
    String updateSql = "UPDATE Categories SET CategoryName=? WHERE CategoryId=?";
    String deleteSql = "DELETE FROM Categories WHERE CategoryId=?";
    String findAllSql = "SELECT * FROM Categories";
    String findByIdSql = "SELECT * FROM Categories WHERE CategoryId=?";
    

    @Override
    public void update(Category entity) {
        Object[] values = {
         entity.getCategoryName(),
        entity.getCategoryId()
        };
        XJdbc.executeUpdate(updateSql, values);    }

    @Override
    public void deleteById(String id) {
        XJdbc.executeUpdate(deleteSql, id);    
    }

    @Override
    public List<Category> findAll() {
        return XQuery.getBeanList(Category.class, findAllSql);    
    }

    @Override
    public Category findById(String id) {
        return XQuery.getSingleBean(Category.class, findByIdSql, id);    
    }

    @Override
    public void create(Category entity) {
        Object[] values = {
        entity.getCategoryId(),
        entity.getCategoryName()
        };
        XJdbc.executeUpdate(createSql, values);   
    }
    
}
