package poly.quanao.dao.impl;

import java.util.List;
import poly.quanao.dao.OrderDetailDAO;
import poly.quanao.entity.OrderDetail;
import poly.quanao.util.XJdbc;
import poly.quanao.util.XQuery;


public class OrderDetailDAOImpl implements OrderDetailDAO {

    String createSql = 
    "INSERT INTO OrderDetails (OrderId, ProductId, UnitPrice, Discount, Quantity) VALUES (?, ?, ?, ?, ?)";

String updateSql = 
    "UPDATE OrderDetails SET OrderId=?, ProductId=?, UnitPrice=?, Discount=?, Quantity=? WHERE OrderDetailId=?";

String deleteSql = 
    "DELETE FROM OrderDetails WHERE OrderDetailId=?";

String findAllSql = 
    "SELECT od.*, p.Name AS productName " +
    "FROM OrderDetails od " +
    "JOIN Products p ON p.ProductId = od.ProductId";

String findByIdSql = 
    "SELECT od.*, p.Name AS productName " +
    "FROM OrderDetails od " +
    "JOIN Products p ON p.ProductId = od.ProductId " +
    "WHERE od.OrderDetailId=?";

String findByOrderIdSql = 
    "SELECT od.*, p.Name AS productName " +
    "FROM OrderDetails od " +
    "JOIN Products p ON p.ProductId = od.ProductId " +
    "WHERE od.OrderId=?";

String findByProductIdSql = 
    "SELECT od.*, p.Name AS productName " +
    "FROM OrderDetails od " +
    "JOIN Products p ON p.ProductId = od.ProductId " +
    "WHERE od.ProductId=?";


    @Override
    public List<OrderDetail> findByOrderId(Long orderId) {
        return XQuery.getBeanList(OrderDetail.class, findByOrderIdSql, orderId);
    }

    @Override
    public List<OrderDetail> findByProductId(String drinkId) {
        return XQuery.getBeanList(OrderDetail.class, findByOrderIdSql, drinkId);
    }



    @Override
    public void update(OrderDetail entity) {
        Object[] values = {
            entity.getOrderId(),
            entity.getProductId(),
            entity.getUnitPrice(),
            entity.getDiscount(),
            entity.getQuantity(),
            entity.getOrderdetailid()
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

    @Override
    public void create(OrderDetail entity) {
        Object[] values = {
            entity.getOrderId(),
            entity.getProductId(),
            entity.getUnitPrice(),
            entity.getDiscount(),
            entity.getQuantity()
        };
        XJdbc.executeUpdate(createSql, values);}

}