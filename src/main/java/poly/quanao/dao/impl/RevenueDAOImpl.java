/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.quanao.dao.impl;

import java.util.Date;
import java.util.List;
import poly.quanao.dao.RevenueDAO;
import poly.quanao.entity.Revenue;
import poly.quanao.entity.Revenue.ByCategory;
import poly.quanao.entity.Revenue.ByUser;
import poly.quanao.util.XQuery;


/**
 *
 * @author Admin
 */
public class RevenueDAOImpl  implements RevenueDAO {

    @Override
    public List<Revenue.ByCategory> getByCategory(Date begin, Date end) {
        String revenueByCategorySql 
                = "SELECT category.Name AS Category, " 
                + "   sum(detail.UnitPrice*detail.Quantity*(1-detail.Discount)) AS Revenue," 
                + "   sum(detail.Quantity) AS Quantity," 
                + "   min(detail.UnitPrice) AS MinPrice," 
                + "   max(detail.UnitPrice) AS MaxPrice," 
                + "   avg(detail.UnitPrice) AS AvgPrice " 
                + "FROM BillDetails detail " 
                + "   JOIN Product product ON products.Id=detail.productId" 
                + "   JOIN Categories category ON category.Id=products.CategoryId" 
                + "   JOIN Orders order ON order.Id=detail.OrderId " 
                + "WHERE order.Status=1 " 
                + "   AND order.Checkout IS NOT NULL " 
                + "   AND order.Checkout BETWEEN ? AND ? " 
                + "GROUP BY category.Name " 
                + "ORDER BY Revenue DESC"; 
        return XQuery.getBeanList(ByCategory.class, revenueByCategorySql, begin, end); 
    }

    @Override
    public List<Revenue.ByUser> getByUser(Date begin, Date end) {
         String revenueByUserSql 
                = "SELECT order.Username AS [User], " 
                + "   sum(detail.UnitPrice*detail.Quantity*(1-detail.Discount)) AS Revenue," 
                + "   count(DISTINCT detail.BillId) AS Quantity," 
                + "   min(bill.Checkin) AS FirstTime," 
                + "   max(bill.Checkin) AS LastTime " 
                + "FROM OrderDetails detail " 
                + "   JOIN Orders order ON order.Id=detail.OrderId " 
                + "WHERE order.Status=1 " 
                + "   AND order.Checkout IS NOT NULL " 
                + "   AND order.Checkout BETWEEN ? AND ? " 
                + "GROUP BY order.Username " 
                + "ORDER BY Revenue DESC"; 
        return XQuery.getBeanList(ByUser.class, revenueByUserSql, begin, end);
    } 
}

