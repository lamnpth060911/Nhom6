/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.quanao.dao.impl;


import java.util.List;
import poly.quanao.dao.ProductsDAO;
import poly.quanao.entity.Products;
import poly.quanao.util.XJdbc;
import poly.quanao.util.XQuery;

/**
 *
 * @author LENOVO
 */
public class ProductDAOImpl implements ProductsDAO{
    String createSql = "INSERT INTO Products(ProductId, ProductName,UnitPrice,Discount,Image,InStock,Color,CategoryId) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    String updateSql = "UPDATE Products SET ProductName=?, UnitPrice=?, Discount=?, Image=?, InStock=?, Color=?, CategoryId=?  WHERE ProductId=? ";
    String deleteSql = "DELETE FROM Products WHERE ProductId=?";
    String findAllSql = "SELECT * FROM Products";
    String findByIdSql = "SELECT * FROM Products WHERE ProductId=?";
    String findByCategoryIdSql = "SELECT ProductId, ProductName,UnitPrice,Discount,Image,InStock,CategoryId FROM Products WHERE CategoryId=?";
    @Override
    public List<Products> findByCategoryId(String categoryId) {
        return XQuery.getBeanList(Products.class, findByCategoryIdSql, categoryId);    
    }

    @Override
    public Products create(Products entity) {
        Object[] values = {
        entity.getId(),
        entity.getName(),
        entity.getUnitPrice(),
        entity.getDiscount(),
        entity.getImage(),       
        entity.isInStock(),
        entity.getCategoryId()
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(Products entity) {
        Object[] values = {
        entity.getName(),
        entity.getUnitPrice(),
        entity.getDiscount(),
        entity.getImage(),       
        entity.isInStock(),
        entity.getCategoryId(),
        entity.getId(),
        entity.getColor(),
        };
        XJdbc.executeUpdate(updateSql, values);   
    }
    

    @Override
    public void deleteById(String id) {
         XJdbc.executeUpdate(deleteSql, id); 
    }

    @Override
    public List<Products> findAll() {
         return XQuery.getBeanList(Products.class, findAllSql);    
    }

    @Override
    public Products findById(String id) {
        return XQuery.getSingleBean(Products.class, findByIdSql, id);  
    }
    
}
