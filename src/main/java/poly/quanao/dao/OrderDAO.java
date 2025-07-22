/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.quanao.dao;

import java.util.Date;
import java.util.List;
import poly.quanao.entity.Order;

/**
 *
 * @author Admin
 */
public interface OrderDAO extends CrudDAO<Order, Long> {
     List<Order> findByUsername(String username);
    List<Order> findByCardId(Integer cardId);
    List<Order> findByTimeRange(Date begin, Date end);
    public Order findServicingByCardId(Integer cardId);
    List<Order> findByUserAndTimeRange(String username, Date begin, Date end);
}
