package poly.quanao.dao.impl;

import java.util.List;
import poly.quanao.dao.ProductsDAO;
import poly.quanao.entity.Products;
import poly.quanao.util.XJdbc;
import poly.quanao.util.XQuery;

public class ProductDAOImpl implements ProductsDAO {

    String createSql = "INSERT INTO Products(ProductId, ProductName, Price, Discount, ImagePath, InStock, CategoryId, Color) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    String updateSql = "UPDATE Products SET ProductName=?, Price=?, Discount=?, ImagePath=?, InStock=?, CategoryId=?, Color=? WHERE ProductId=?";
    String deleteSql = "DELETE FROM Products WHERE ProductId=?";
    String findAllSql = "SELECT * FROM Products";
    String findByIdSql = "SELECT * FROM Products WHERE ProductId=?";
    String findByCategoryIdSql = "SELECT * FROM Products WHERE CategoryId=?";

    public Products create(Products entity) {
        Object[] values = {
            entity.getId(),
            entity.getName(),
            entity.getUnitPrice(),
            entity.getDiscount(),
            entity.getImage(),
            entity.isInStock(),
            entity.getCategoryId(),
            entity.getColor()
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

public void update(Products entity) {
    Object[] values = {
        entity.getName(),     // ✅ Fixed casing
        entity.getUnitPrice(),
        entity.getDiscount(),
        entity.getImage(),
        entity.isInStock(),
        entity.getCategoryId(),
        entity.getColor(),
        entity.getId()
    };
    XJdbc.executeUpdate(updateSql, values);
}
    public void deleteById(String productId) {
        XJdbc.executeUpdate(deleteSql, productId);
    }

    public List<Products> findAll() {
        return XQuery.getBeanList(Products.class, findAllSql);
    }

    public Products findById(String productId) {
        return XQuery.getSingleBean(Products.class, findByIdSql, productId);
    }

    public List<Products> findByCategoryId(String categoryId) {
        return XQuery.getBeanList(Products.class, findByCategoryIdSql, categoryId);
    }
}