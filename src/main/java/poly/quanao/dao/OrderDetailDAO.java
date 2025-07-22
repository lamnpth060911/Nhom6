/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.quanao.dao;

import java.util.List;
import poly.quanao.entity.OrderDetail;

/**
 *
 * @author LENOVO
 */
public interface OrderDetailDAO extends CrudDAO<OrderDetail, Long>{
    List<OrderDetail> findByBillId(Long OrderId);
    List<OrderDetail> findByDrinkId(String ProductsId);
}
