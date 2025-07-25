package poly.quanao.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import poly.quanao.dao.OrderDAO;
import poly.quanao.entity.Order;
import poly.quanao.util.XJdbc;
import poly.quanao.util.XAuth;
import poly.quanao.util.XQuery;

public class OrderDAOImpl implements OrderDAO {

    String createSql = 
    "INSERT INTO Orders (Username, CardId, Checkin, Checkout, Status) VALUES (?, ?, ?, ?, ?)";

String updateSql = 
    "UPDATE Orders SET Username=?, CardId=?, Checkin=?, Checkout=?, Status=? WHERE OrderId=?";

String deleteSql = 
    "DELETE FROM Orders WHERE OrderId=?";

String findAllSql = 
    "SELECT * FROM Orders";

String findByIdSql = 
    "SELECT * FROM Orders WHERE OrderId=?";

String findByUsernameSql = 
    "SELECT * FROM Orders WHERE Username=?";

String findByCardIdSql = 
    "SELECT * FROM Orders WHERE CardId=?";

String findByTimeRangeSql = 
    "SELECT * FROM Orders WHERE Checkin BETWEEN ? AND ? ORDER BY Checkin DESC";


    // ✅ Map ResultSet -> Order

    @Override
    public List<Order> findByUsername(String username) {
        return XQuery.getBeanList(Order.class, findByUsernameSql, username);    
    }

    @Override
    public List<Order> findByCardId(Integer cardId) {
       return XQuery.getBeanList(Order.class, findByCardIdSql, cardId);
    }

    @Override
    public List<Order> findByTimeRange(Date begin, Date end) {
        return XQuery.getBeanList(Order.class, findByTimeRangeSql, begin, end);
    }

    @Override
public Order findServicingByCardId(Integer cardId) {
    String sql = "SELECT * FROM Orders WHERE CardId=? AND Status=0";
    Order bill = XQuery.getSingleBean(Order.class, sql, cardId);

    if (bill == null) { // không tìm thấy -> tạo mới
        Order newBill = new Order();
        newBill.setCardId(cardId);
        newBill.setCheckin(new Date());
        newBill.setStatus(0); // đang phục vụ
        newBill.setUsername(XAuth.user.getUsername());

        // Chỉ insert
        this.create(newBill);

        // Sau khi insert, gọi lại để lấy bản ghi mới
        bill = XQuery.getSingleBean(Order.class, sql, cardId);
    }
    return bill;
}


    @Override
    public List<Order> findByUserAndTimeRange(String username, Date begin, Date end) {
        String sql = "SELECT * FROM Bills " + " WHERE Username=? AND Checkin BETWEEN ? AND ?";
        return XQuery.getBeanList(Order.class, sql, username, begin, end);
    }

    @Override
    public void create(Order entity) {
        Object[] values = {
        entity.getUsername(),        
        entity.getCardId(),
        entity.getCheckin(),
        entity.getCheckout(),
        entity.getStatus()
        };
        XJdbc.executeUpdate(createSql, values);
    }

    @Override
    public void update(Order entity) {
        Object[] values = {
        entity.getUsername(),
        entity.getCardId(),
        entity.getCheckin(),
        entity.getCheckout(),
        entity.getStatus(),
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
}
