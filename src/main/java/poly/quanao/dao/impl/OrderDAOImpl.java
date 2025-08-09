package poly.quanao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
public void deleteById(Long orderId) {
    if (orderId == null) return;

    final String SQL_DEL_DETAILS = "DELETE FROM OrderDetails WHERE OrderId = ?";
    final String SQL_DEL_ORDER   = "DELETE FROM Orders WHERE OrderId = ?";

    Connection conn = null;
    try {
        conn = XJdbc.openConnection();
        conn.setAutoCommit(false);

        try (PreparedStatement ps = conn.prepareStatement(SQL_DEL_DETAILS)) {
            ps.setLong(1, orderId);
            ps.executeUpdate(); // xoá hết chi tiết của đơn
        }

        int affected;
        try (PreparedStatement ps = conn.prepareStatement(SQL_DEL_ORDER)) {
            ps.setLong(1, orderId);
            affected = ps.executeUpdate(); // xoá đơn
        }
        if (affected == 0) throw new RuntimeException("OrderId không tồn tại: " + orderId);

        conn.commit();
    } catch (Exception ex) {
        if (conn != null) try { conn.rollback(); } catch (Exception ignore) {}
        throw new RuntimeException(ex);
    } finally {
        if (conn != null) try { conn.setAutoCommit(true); conn.close(); } catch (Exception ignore) {}
    }
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
