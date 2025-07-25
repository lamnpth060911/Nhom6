package poly.quanao.dao.impl;

import java.util.List;
import poly.quanao.dao.ProductsDAO;
import poly.quanao.entity.Products;
import poly.quanao.util.XJdbc;
import poly.quanao.util.XQuery;

public class ProductsDAOImpl implements ProductsDAO {

    String createSql
            = "INSERT INTO Products(ProductId, ProductName, Price, Discount, InStock, CategoryId, Color , ImagePath) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

    String updateSql
            = "UPDATE Products SET ProductName=?, Price=?, Discount=?, CategoryId=?, InStock=?, Color=?, ImagePath=? WHERE ProductId=?";

    String deleteSql
            = "DELETE FROM Products WHERE ProductId=?";

    String findAllSql
            = "SELECT * FROM Products";

    String findByIdSql
            = "SELECT * FROM Products WHERE ProductId=?";

    String findByCategoryIdSql
            = "SELECT ProductId, ProductName, Price, Discount, InStock, CategoryId ,Color,ImagePath FROM Products WHERE CategoryId=?";

    @Override
    public void create(Products entity) {
        XJdbc.executeUpdate(createSql,
                entity.getProductId(),
                entity.getProductName(),
                entity.getPrice(),
                entity.getDiscount(),
                entity.isInStock() ? 1 : 0, // ✅ ép boolean → 1/0
                entity.getCategoryId(),
                entity.getColor(),
                entity.getImagePath()
        );
    }

    @Override
    public void update(Products entity) {
        XJdbc.executeUpdate(updateSql,
                entity.getProductId(),
                entity.getProductName(),
                entity.getPrice(),
                entity.getDiscount(),
                entity.isInStock() ? 1 : 0, // ✅ ép boolean → 1/0
                entity.getCategoryId(),
                entity.getColor(),
                entity.getImagePath()
        );
    }

    @Override
    public void deleteById(Integer ProductId) {  // ✅ Dùng Integer
        XJdbc.executeUpdate(deleteSql, ProductId);
    }

    @Override
    public Products findById(Integer ProductId) {
        List<Products> list = XQuery.getBeanList(Products.class, findByIdSql, ProductId);
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
