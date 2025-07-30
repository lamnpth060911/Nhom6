package poly.quanao.dao.impl;

import java.util.Date;
import java.util.List;
import poly.quanao.dao.RevenueDAO;
import poly.quanao.entity.Revenue;
import poly.quanao.entity.Revenue.ByCategory;
import poly.quanao.entity.Revenue.ByUser;
import poly.quanao.util.XQuery;

public class RevenueDAOImpl implements RevenueDAO {

    @Override
    public List<Revenue.ByCategory> getByCategory(Date begin, Date end) {
        String revenueByCategorySql
                = "SELECT c.CategoryName AS Category, "
                + "   SUM(od.UnitPrice * od.Quantity * (1 - od.Discount)) AS Revenue, "
                + "   SUM(od.Quantity) AS Quantity, "
                + "   MIN(od.UnitPrice) AS MinPrice, "
                + "   MAX(od.UnitPrice) AS MaxPrice, "
                + "   AVG(od.UnitPrice) AS AvgPrice "
                + "FROM OrderDetails od "
                + "JOIN Products p ON p.ProductId = od.ProductId "
                + "JOIN Categories c ON c.CategoryId = p.CategoryId "
                + "JOIN Orders o ON o.OrderId = od.OrderId "
                + "WHERE o.Status = 1 "
                + "  AND o.Checkout IS NOT NULL "
                + "  AND o.Checkout BETWEEN ? AND ? "
                + "GROUP BY c.CategoryName "
                + "ORDER BY Revenue DESC";

        return XQuery.getBeanList(ByCategory.class, revenueByCategorySql, begin, end);
    }

    @Override
    public List<Revenue.ByUser> getByUser(Date begin, Date end) {
        String revenueByUserSql
                = "SELECT o.Username AS [User], "
                + "   SUM(od.UnitPrice * od.Quantity * (1 - od.Discount)) AS Revenue, "
                + "   COUNT(DISTINCT o.OrderId) AS Quantity, "
                + "   MIN(o.Checkin) AS FirstTime, "
                + "   MAX(o.Checkin) AS LastTime "
                + "FROM OrderDetails od "
                + "JOIN Orders o ON o.OrderId = od.OrderId "
                + "WHERE o.Status = 1 "
                + "  AND o.Checkout IS NOT NULL "
                + "  AND o.Checkout BETWEEN ? AND ? "
                + "GROUP BY o.Username "
                + "ORDER BY Revenue DESC";

        return XQuery.getBeanList(ByUser.class, revenueByUserSql, begin, end);
    }
}
