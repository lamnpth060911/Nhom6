package poly.quanao.dao.impl;

import java.util.List;
import poly.quanao.dao.ProductsDAO;
import poly.quanao.entity.Products;
import poly.quanao.util.XJdbc;
import poly.quanao.util.XQuery;

public class ProductsDAOImpl implements ProductsDAO {

    String createSql = "INSERT INTO Products(ProductName, Price, Discount, Quantity, CategoryId, Color, ImagePath) VALUES(?, ?, ?, ?, ?, ?, ?)";

    String updateSql = "UPDATE Products SET ProductName=?, Price=?, Discount=?, Quantity=?, CategoryId=?, Color=?, ImagePath=? WHERE ProductId=?";

    String deleteSql = "DELETE FROM Products WHERE ProductId=?";

    String findAllSql = "SELECT * FROM Products";

    String findByIdSql = "SELECT * FROM Products WHERE ProductId=?";

    String findByCategoryIdSql = "SELECT * FROM Products WHERE CategoryId=?";

    @Override
    public void create(Products entity) {
        XJdbc.executeUpdate(createSql,
                entity.getProductName(),
                entity.getPrice(),
                entity.getDiscount(),
                entity.getQuantity(), // ✅ mới thêm
                entity.getCategoryId(),
                entity.getColor(),
                entity.getImagePath()
        );
    }

    @Override
    public void update(Products entity) {
        XJdbc.executeUpdate(updateSql,
                entity.getProductName(),
                entity.getPrice(),
                entity.getDiscount(),
                entity.getQuantity(), // ✅ mới thêm
                entity.getCategoryId(),
                entity.getColor(),
                entity.getImagePath(),
                entity.getProductId()
        );
    }

    @Override
    public void deleteById(Integer productId) {
        XJdbc.executeUpdate(deleteSql, productId);
    }

    @Override
    public Products findById(Integer productId) {
        List<Products> list = XQuery.getBeanList(Products.class, findByIdSql, productId);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Products> findAll() {
        return XQuery.getBeanList(Products.class, findAllSql);
    }

    @Override
    public List<Products> findByCategoryId(String categoryId) {
        return XQuery.getBeanList(Products.class, findByCategoryIdSql, categoryId);
    }
}
