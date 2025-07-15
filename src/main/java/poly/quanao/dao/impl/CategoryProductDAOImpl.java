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
String createSql = "INSERT INTO Categories(CategoryId, CategoryName) VALUES(?, ?)";
String updateSql = "UPDATE Categories SET Name=? WHERE CategoryId=?";
String deleteSql = "DELETE FROM Categories WHERE CategoryId=?";
String findAllSql = "SELECT * FROM Categories";
String findByIdSql = "SELECT * FROM Categories WHERE CategoryId=?";
    @Override
    public ProductCategory create(ProductCategory entity) {
        Object[] values = {
        entity.getCategoryId(),
        entity.getCategoryName()
        };
        XJdbc.executeUpdate(createSql, values);
 return entity;    }

    @Override
    public void update(ProductCategory entity) {
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
    public List<ProductCategory> findAll() {
        return XQuery.getBeanList(ProductCategory.class, findAllSql);    
    }

    @Override
    public ProductCategory findById(String id) {
        return XQuery.getSingleBean(ProductCategory.class, findByIdSql, id);    
    }
    
}

    
