package poly.quanao.dao.impl;

import java.util.Date;
import java.util.List;
import poly.quanao.dao.OrderDAO;
import poly.quanao.entity.Order;
import poly.quanao.util.XJdbc;
import poly.quanao.util.XQuery;

public class OrderDAOImpl implements OrderDAO {

    String createSql = 
        "INSERT INTO Orders (Username, Checkin, Checkout, Status, Color) VALUES (?, ?, ?, ?, ?)";

    String updateSql = 
        "UPDATE Orders SET Username=?, Checkin=?, Checkout=?, Status=?, Color=? WHERE OrderId=?";

    String deleteSql = 
        "DELETE FROM Orders WHERE OrderId=?";

    String findAllSql = 
        "SELECT * FROM Orders";

    String findByIdSql = 
        "SELECT * FROM Orders WHERE OrderId=?";

    String findByUsernameSql = 
        "SELECT * FROM Orders WHERE Username=?";

    String findByTimeRangeSql = 
        "SELECT * FROM Orders WHERE Checkin BETWEEN ? AND ? ORDER BY Checkin DESC";

    @Override
    public List<Order> findByUsername(String username) {
        return XQuery.getBeanList(Order.class, findByUsernameSql, username);    
    }

    @Override
    public List<Order> findByTimeRange(Date begin, Date end) {
        return XQuery.getBeanList(Order.class, findByTimeRangeSql, begin, end);
    }

    @Override
    public List<Order> findByUserAndTimeRange(String username, Date begin, Date end) {
        String sql = "SELECT * FROM Orders WHERE Username=? AND Checkin BETWEEN ? AND ?";
        return XQuery.getBeanList(Order.class, sql, username, begin, end);
    }

    @Override
    public void create(Order entity) {
        Object[] values = {
            entity.getUsername(),
            entity.getCheckin(),
            entity.getCheckout(),
            entity.getStatus(),
            entity.getColor() // thêm color vào
        };
        XJdbc.executeUpdate(createSql, values);
    }

    @Override
    public void update(Order entity) {
        Object[] values = {
            entity.getUsername(),
            entity.getCheckin(),
            entity.getCheckout(),
            entity.getStatus(),
            entity.getColor(), // thêm color vào
            entity.getOrderId()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(Long id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public Order findById(Long id) {
        return XQuery.getSingleBean(Order.class, findByIdSql, id);  
    }

    @Override
    public List<Order> findAll() {
        return XQuery.getBeanList(Order.class, findAllSql);
    }

    @Override
    public Long createAndReturnId(Order entity) {
        String sql = "INSERT INTO Orders (Username, Checkin, Checkout, Status, Color) OUTPUT INSERTED.OrderId VALUES (?, ?, ?, ?, ?)";
        Object[] values = {
            entity.getUsername(),
            entity.getCheckin(),
            entity.getCheckout(),
            entity.getStatus(),
            entity.getColor() // thêm color vào
        };
        
        Object result = XJdbc.getValue(sql, values);
        return result == null ? null : ((Number) result).longValue();
    }
}
