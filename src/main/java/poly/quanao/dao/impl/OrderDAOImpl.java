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

    // INSERT: bỏ Color
    String createSql =
        "INSERT INTO Orders (Username, Checkin, Checkout, Status) VALUES (?, ?, ?, ?)";

    // UPDATE: bỏ Color
    String updateSql =
        "UPDATE Orders SET Username=?, Checkin=?, Checkout=?, Status=? WHERE OrderId=?";

    String deleteSql =
        "DELETE FROM Orders WHERE OrderId=?";

    // SELECT: bỏ Color
    String findAllSql =
        "SELECT " +
        "  OrderId  AS orderId, " +
        "  Username AS username, " +
        "  Checkin  AS checkin, " +
        "  Checkout AS checkout, " +
        "  Status   AS status " +
        "FROM Orders";

    String findByIdSql =
        "SELECT " +
        "  OrderId  AS orderId, " +
        "  Username AS username, " +
        "  Checkin  AS checkin, " +
        "  Checkout AS checkout, " +
        "  Status   AS status " +
        "FROM Orders WHERE OrderId = ?";

    String findByUsernameSql =
        "SELECT " +
        "  OrderId  AS orderId, " +
        "  Username AS username, " +
        "  Checkin  AS checkin, " +
        "  Checkout AS checkout, " +
        "  Status   AS status " +
        "FROM Orders WHERE Username = ?";

    String findByTimeRangeSql =
        "SELECT " +
        "  OrderId  AS orderId, " +
        "  Username AS username, " +
        "  Checkin  AS checkin, " +
        "  Checkout AS checkout, " +
        "  Status   AS status " +
        "FROM Orders " +
        "WHERE Checkin BETWEEN ? AND ? " +
        "ORDER BY Checkin DESC";

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
        String sql =
            "SELECT OrderId AS orderId, Username AS username, Checkin AS checkin, " +
            "       Checkout AS checkout, Status AS status " +
            "FROM Orders WHERE Username=? AND Checkin BETWEEN ? AND ?";
        return XQuery.getBeanList(Order.class, sql, username, begin, end);
    }

    @Override
    public void create(Order entity) {
        XJdbc.executeUpdate(createSql,
            entity.getUsername(),
            entity.getCheckin(),
            entity.getCheckout(),
            entity.getStatus()
        );
    }

    @Override
    public void update(Order entity) {
        XJdbc.executeUpdate(updateSql,
            entity.getUsername(),
            entity.getCheckin(),
            entity.getCheckout(),
            entity.getStatus(),
            entity.getOrderId()
        );
    }

    @Override
public void deleteById(Long orderId) {
    // Xoá chi tiết trước
    String sqlDetails = "DELETE FROM OrderDetails WHERE OrderId = ?";
    XJdbc.executeUpdate(sqlDetails, orderId);

    // Xoá đơn hàng
    String sqlOrder = "DELETE FROM Orders WHERE OrderId = ?";
    XJdbc.executeUpdate(sqlOrder, orderId);
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
        String sql =
            "INSERT INTO Orders (Username, Checkin, Checkout, Status) " +
            "OUTPUT INSERTED.OrderId VALUES (?, ?, ?, ?)";
        Object result = XJdbc.getValue(sql,
            entity.getUsername(),
            entity.getCheckin(),
            entity.getCheckout(),
            entity.getStatus()
        );
        return result == null ? null : ((Number) result).longValue();
    }
}
