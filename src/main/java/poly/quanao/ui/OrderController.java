/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.quanao.ui;

import poly.quanao.entity.Order;

/**
 *
 * @author ADMIN
 */
public interface OrderController {
     void setOrder(Order order); 
    void open(); // Hiển thị bill
    void close(); // Xóa bill nếu ko chứa đồ uống nào
    void showProductsJDialog(); 
    void removeProduct(); // Xóa đồ uống khỏi bil
    void updateQuantity(); // Thay đổi số lượng đồ uống
    void checkout(); // Thanh toán
    void cancel(); // Hủy bill
    void fillOrderDetails();
    void createNewOrder(); 
}
