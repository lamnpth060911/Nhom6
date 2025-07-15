/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.quanao.dao.impl;

import java.util.List;

import poly.quanao.util.XJdbc;
import poly.quanao.util.XQuery;
import poly.quanao.dao.CategoryProductDAO;
import poly.quanao.entity.ProductCategory;

/**
 *
 * @author ADMIN
 */
public class CategoryProductDAOImpl implements CategoryProductDAO{
    String createSql = "INSERT INTO Categories(Id, Name) VALUES(?, ?)";
    String updateSql = "UPDATE Categories SET Name=? WHERE Id=?";
    String deleteSql = "DELETE FROM Categories WHERE Id=?";
    String findAllSql = "SELECT * FROM Categories";
    String findByIdSql = "SELECT * FROM Categories WHERE Id=?";
    @Override
    public ProductCategory create(ProductCategory entity) {
        Object[] values = {
        entity.getId(),
        entity.getName()
        };
        XJdbc.executeUpdate(createSql, values);
 return entity;    }

    @Override
    public void update(ProductCategory entity) {
        Object[] values = {
         entity.getName(),
        entity.getId()
        };
        XJdbc.executeUpdate(updateSql, values);    }

    @Override
    public void deleteById(String id) {
        XJdbc.executeUpdate(deleteSql, id);    
    }

    @Override
    public List<ProductCategory> findAll() {
        return XQuery.getBeanList(ProductCategory.class, findAllSql);    
    }

    @Override
    public ProductCategory findById(String id) {
        return XQuery.getSingleBean(ProductCategory.class, findByIdSql, id);    
    }
    
}

    
