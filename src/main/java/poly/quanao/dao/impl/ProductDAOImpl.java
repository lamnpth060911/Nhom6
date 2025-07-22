package poly.quanao.dao.impl;



import java.util.List;
import poly.quanao.dao.ProductsDAO;
import poly.quanao.entity.Products;
import poly.quanao.util.XJdbc;
import poly.quanao.util.XQuery;

public class ProductDAOImpl implements ProductsDAO {

    private final String createSql = 
        "INSERT INTO Products(ProductId, ProductName, UnitPrice, Discount, Image, InStock, Color, CategoryId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private final String updateSql = 
        "UPDATE Products SET ProductName=?, UnitPrice=?, Discount=?, Image=?, InStock=?, Color=?, CategoryId=? WHERE ProductId=?";

    private final String deleteSql = 
        "DELETE FROM Products WHERE ProductId=?";

    private final String findAllSql = 
        "SELECT * FROM Products";

    private final String findByIdSql = 
        "SELECT * FROM Products WHERE ProductId=?";

    private final String findByCategoryIdSql = 
        "SELECT * FROM Products WHERE CategoryId=?";

    @Override
    public Products create(Products entity) {
        Object[] values = {
            entity.getId(),
            entity.getName(),
            entity.getUnitPrice(),
            entity.getDiscount(),
            entity.getImage(),
            entity.isInStock(),
            entity.getColor(),
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
            entity.getColor(),
            entity.getCategoryId(),
            entity.getId()
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

    @Override
    public List<Products> findByCategoryId(String categoryId) {
        return XQuery.getBeanList(Products.class, findByCategoryIdSql, categoryId);
    }
}