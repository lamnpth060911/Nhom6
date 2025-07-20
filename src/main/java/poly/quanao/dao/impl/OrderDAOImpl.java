/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.quanao.dao.impl;

import java.util.Date;
import java.util.List;
import poly.quanao.dao.OrderDAO;
import poly.quanao.entity.Order;

import poly.quanao.util.XJdbc;
import poly.quanao.util.XQuery;
import poly.quanao.util.XAuth;

/**
 *
 * @author LENOVO
 */
public class OrderDAOImpl implements OrderDAO{
    String createSql = "INSERT INTO Bills(Username, CardId, Checkin, Checkout, Status) VALUES(?, ?, ?, ?, ?);";
    String updateSql = "UPDATE Bills SET Username=?, CardId=?, Checkin=?, Checkout=?, Status=? WHERE Id=?";
    String deleteSql = "DELETE FROM Bills WHERE Id=?";
    String findAllSql = "SELECT * FROM Bills";
    String findByIdSql = "SELECT * FROM Bills WHERE Id=?";
    String findByUsernameSql = "SELECT * FROM Bills WHERE Username=?";
    String findByCardIdSql = "SELECT * FROM Bills WHERE CardId=?"; 
    String findByTimeRangeSql = "SELECT * FROM Bills WHERE Checkin BETWEEN ? AND ? ORDER BY Checkin DESC";
    public List<Order> findByTimeRange(Date begin, Date end) {
        return XQuery.getBeanList(Order.class, findByTimeRangeSql, begin, end);
    }
   
    public List<Order> findByUsername(String username) {
        return XQuery.getBeanList(Order.class, findByUsernameSql, username);    
    }

    public List<Order> findByCardId(Integer cardId) {
        return XQuery.getBeanList(Order.class, findByCardIdSql, cardId);
    }

    @Override
    public Order create(Order entity) {
        Object[] values = {
        entity.getUsername(),        
        entity.getCardId(),
        entity.getCheckin(),
        entity.getCheckout(),
        entity.getStatus()
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(Order entity) {
        Object[] values = {
        entity.getUsername(),
        entity.getCardId(),
        entity.getCheckin(),
        entity.getCheckout(),
        entity.getStatus(),
        entity.getId()
        };
        XJdbc.executeUpdate(updateSql, values);
        
    }

    @Override
    public void deleteById(Long id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<Order> findAll() {
    return XQuery.getBeanList(Order.class, findAllSql);       
    }

    @Override
    public Order findById(Long id) {
        return XQuery.getSingleBean(Order.class, findByIdSql, id);  
    }  

    public Order findServicingByCardId(Integer cardId) {
        String sql = "SELECT * FROM Bills WHERE CardId=? AND Status=0";
        Order bill = XQuery.getSingleBean(Order.class, sql, cardId);
        if (bill == null) { // không tìm thấy -> tạo mới
            Order newBill = new Order();
            newBill.setCardId(cardId);
            newBill.setCheckin(new Date());
            newBill.setStatus(0); // đang phục vụ
            newBill.setUsername(XAuth.user.getUsername());
            bill = this.create(newBill); // insert
        }
        return bill;
    }

    public List<Order> findByUserAndTimeRange(String username, Date begin, Date end) {
        String sql = "SELECT * FROM Bills " + " WHERE Username=? AND Checkin BETWEEN ? AND ?";
        return XQuery.getBeanList(Order.class, sql, username, begin, end);
    }
}

