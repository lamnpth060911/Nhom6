package poly.quanao.dao.impl;

import java.util.List;
import poly.quanao.dao.OrderDetailDAO;
import poly.quanao.entity.OrderDetail;
import poly.quanao.util.XJdbc;
import poly.quanao.util.XQuery;


public class OrderDetailDAOImpl implements OrderDetailDAO {

    String createSql = "INSERT INTO OrderDetails(OrderId, ProductId, UnitPrice, Discount, Quantity) VALUES(?, ?, ?, ?, ?)";
    String updateSql = "UPDATE OrderDetails SET OrderId=?, ProductId=?, UnitPrice=?, Discount=?, Quantity=? WHERE Id=?";
    String deleteSql = "DELETE FROM OrderDetails WHERE Id=?";
    String findAllSql = "SELECT bd.*, d.name AS productsName FROM OrderDetails bd JOIN Products d ON d.Id=bd.ProductId";
    String findByIdSql = "SELECT bd.*, d.name AS productsName FROM OrderDetails bd JOIN Products d ON d.Id=bd.ProductId WHERE bd.Id=?";
    String findByOrderIdSql = "SELECT bd.*, d.name AS productName FROM OrderDetails bd JOIN Products d ON d.Id=bd.ProductId WHERE bd.OrderId=?";
    String findByDrinkIdSql = "SELECT bd.*, d.name AS productName FROM OrderDetails bd JOIN Products d ON d.Id=bd.ProductId WHERE bd.ProductsId=?";

    @Override
    public List<OrderDetail> findByOrderId(Long orderId) {
        return XQuery.getBeanList(OrderDetail.class, findByOrderIdSql, orderId);
    }

    @Override
    public List<OrderDetail> findByProductId(String drinkId) {
        return XQuery.getBeanList(OrderDetail.class, findByDrinkIdSql, drinkId);
    }

    @Override
    public OrderDetail create(OrderDetail entity) {
        Object[] values = {
            entity.getOrderId(),
            entity.getProductId(),
            entity.getUnitPrice(),
            entity.getDiscount(),
            entity.getQuantity()
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(OrderDetail entity) {
        Object[] values = {
            entity.getOrderId(),
            entity.getProductId(),
            entity.getUnitPrice(),
            entity.getDiscount(),
            entity.getQuantity(),
            entity.getId()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(Long id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<OrderDetail> findAll() {
        return XQuery.getBeanList(OrderDetail.class, findAllSql);
    }

    @Override
    public OrderDetail findById(Long id) {
        return XQuery.getSingleBean(OrderDetail.class, findByIdSql, id);
    }

}