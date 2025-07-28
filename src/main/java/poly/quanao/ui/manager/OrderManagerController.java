/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.quanao.ui.manager;

import poly.quanao.entity.Order;

/**
 *
 * @author Admin
 */
public interface OrderManagerController extends CrudController<Order> {
    void fillOrderDetails(); // tải và hiển thị chi tiết phiếu
    void selectTimeRange(); // xử lý chọn khoảng thời gian trong cboTimeRanges
}
