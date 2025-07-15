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
 * @author ADMIN
 */
public class ProductDAOImpl implements ProductsDAO{
  String createSql = "INSERT INTO Products(ProductId, ProductName, UnitPrice, Discount, ImagePath, InStock, CategoryId) VALUES (?, ?, ?, ?, ?, ?, ?)";
String updateSql = "UPDATE Products SET ProductName=?, UnitPrice=?, Discount=?, ImagePath=?, InStock=?, CategoryId=? WHERE ProductId=?";
String deleteSql = "DELETE FROM Products WHERE ProductId=?";
String findAllSql = "SELECT * FROM Products";
String findByIdSql = "SELECT * FROM Products WHERE ProductId=?";
String findByCategoryIdSql = "SELECT ProductId, ProductName, UnitPrice, Discount, ImagePath, InStock, CategoryId FROM Products WHERE CategoryId=?";
    @Override
    public List<Products> findByCategoryId(String categoryId) {
        return XQuery.getBeanList(Products.class, findByCategoryIdSql, categoryId);    
    }

@Override
public void update(Products entity) {
    Object[] values = {
        entity.getProductName(),
        entity.getPrice(),
        entity.getDiscount(),
        entity.getImagePath(),
        entity.isInStock(),
        entity.getCategoryId(),
        entity.getProductId() // điều kiện WHERE
    };
    XJdbc.executeUpdate(updateSql, values);
}

@Override
public void deleteById(String productId) {
    XJdbc.executeUpdate(deleteSql, productId);
}

@Override
public List<Products> findAll() {
    return XQuery.getBeanList(Products.class, findAllSql);
}

@Override
public Products findById(String productId) {
    return XQuery.getSingleBean(Products.class, findByIdSql, productId);
}
return XQuery.getSingleBean(Drink.class, findByIdSql, id);  
    }
    