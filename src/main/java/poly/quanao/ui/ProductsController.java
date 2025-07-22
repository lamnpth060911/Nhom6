/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.quanao.ui;

import poly.quanao.entity.Order;

/**
 *
 * @author Admin
 */
public interface ProductsController {
    void setOrder(Order order); // nhận bill từ OrderJDialog
    void open(); // hiển thị loại và đồ uống
    void fillCategories(); // tải và hiển thị loại đồ uống
    void fillProducts(); // tải và hiển thị đồ uống
    void addProductToOrder(); // thêm đồ uống vào bil
}
