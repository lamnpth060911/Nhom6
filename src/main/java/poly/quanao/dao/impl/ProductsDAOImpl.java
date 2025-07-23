package poly.quanao.dao.impl;

import java.util.List;
import poly.quanao.dao.ProductsDAO;
import poly.quanao.entity.Products;
import poly.quanao.util.XJdbc;
import poly.quanao.util.XQuery;

public class ProductsDAOImpl implements ProductsDAO {

    String createSql = 
        "INSERT INTO Products(ProductId, ProductName, Price, Discount, ImagePath, InStock, Color, CategoryId) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    
    String updateSql = 
        "UPDATE Products SET ProductName=?, Price=?, Discount=?, ImagePath=?, InStock=?, Color=?, CategoryId=? WHERE ProductId=?";
    
    String deleteSql = 
        "DELETE FROM Products WHERE ProductId=?";
    
    String findAllSql = 
        "SELECT * FROM Products";
    
    String findByIdSql = 
        "SELECT * FROM Products WHERE ProductId=?";
    
    String findByCategoryIdSql = 
        "SELECT ProductId, ProductName, Price, Discount, ImagePath, InStock, CategoryId FROM Products WHERE CategoryId=?";

    @Override
public void create(Products entity) {
    XJdbc.executeUpdate(createSql,
        entity.getProductId(),
        entity.getProductName(),
        entity.getPrice(),
        entity.getDiscount(),
        entity.getImagePath(),
        entity.isInStock() ? 1 : 0,   // ✅ ép boolean sang 1/0
        entity.getColor(),
        entity.getCategoryId()
    );
}


    @Override
public void update(Products entity) {
    XJdbc.executeUpdate(updateSql,
        entity.getProductName(),
        entity.getPrice(),
        entity.getDiscount(),
        entity.getImagePath(),
        entity.isInStock() ? 1 : 0,  // ✅ ép boolean → 1/0
        entity.getColor(),
        entity.getCategoryId(),
        entity.getProductId()
    );
}


    @Override
    public void deleteById(Integer id) {  // ✅ Dùng Integer
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
public Products findById(Integer id) {
    List<Products> list = XQuery.getBeanList(Products.class, findByIdSql, id);
    return list.isEmpty() ? null : list.get(0);
}


    @Override
    public List<Products> findAll() {
        return XQuery.getBeanList(Products.class, findAllSql);
    }

    @Override
    public List<Products> findByCategoryId(int categoryId) {
        return XQuery.getBeanList(Products.class, findByCategoryIdSql, categoryId);
    }
}
