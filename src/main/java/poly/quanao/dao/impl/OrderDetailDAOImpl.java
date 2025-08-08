package poly.quanao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import poly.quanao.dao.OrderDetailDAO;
import poly.quanao.entity.OrderDetail;
import poly.quanao.util.XDialog;
import poly.quanao.util.XJdbc;
import poly.quanao.util.XQuery;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    String createSql =
        "INSERT INTO OrderDetails (OrderId, ProductId, UnitPrice, Discount, Quantity, Color) VALUES (?, ?, ?, ?, ?, ?)";

    String updateSql =
        "UPDATE OrderDetails SET OrderId=?, ProductId=?, UnitPrice=?, Discount=?, Quantity=?, Color=? WHERE OrderDetailId=?";

    String deleteSql =
        "DELETE FROM OrderDetails WHERE OrderDetailId=?";

    String findAllSql =
        "SELECT od.OrderDetailId, od.OrderId, od.ProductId, od.UnitPrice, od.Discount, od.Quantity, od.Color, " +
        "p.ProductName AS productName " +
        "FROM OrderDetails od " +
        "JOIN Products p ON p.ProductId = od.ProductId";

    String findByIdSql =
        "SELECT od.OrderDetailId, od.OrderId, od.ProductId, od.UnitPrice, od.Discount, od.Quantity, od.Color, " +
        "p.ProductName AS productName " +
        "FROM OrderDetails od " +
        "JOIN Products p ON p.ProductId = od.ProductId " +
        "WHERE od.OrderDetailId=?";

    String findByOrderIdSql =
        "SELECT od.OrderDetailId, od.OrderId, od.ProductId, od.UnitPrice, od.Discount, od.Quantity, od.Color, " +
        "p.ProductName AS productName " +
        "FROM OrderDetails od " +
        "JOIN Products p ON p.ProductId = od.ProductId " +
        "WHERE od.OrderId=?";

    String findByProductIdSql =
        "SELECT od.OrderDetailId, od.OrderId, od.ProductId, od.UnitPrice, od.Discount, od.Quantity, od.Color, " +
        "p.ProductName AS productName " +
        "FROM OrderDetails od " +
        "JOIN Products p ON p.ProductId = od.ProductId " +
        "WHERE od.ProductId=?";

    @Override
    public List<OrderDetail> findByOrderId(Long orderId) {
        return XQuery.getBeanList(OrderDetail.class, findByOrderIdSql, orderId);
    }

    @Override
    public List<OrderDetail> findByProductId(String productId) {
        return XQuery.getBeanList(OrderDetail.class, findByProductIdSql, productId);
    }

    @Override
    public void update(OrderDetail entity) {
        Object[] values = {
            entity.getOrderId(),
            entity.getProductId(),
            entity.getUnitPrice(),
            entity.getDiscount(),
            entity.getQuantity(),
            entity.getColor(), // thêm color
            entity.getOrderDetailId()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(Long id) {
        // Xóa chi tiết đơn hàng
        String deleteOrderDetailsSql = "DELETE FROM OrderDetails WHERE OrderId = ?";
        XJdbc.executeUpdate(deleteOrderDetailsSql, id);

        // Xóa đơn hàng chính
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<OrderDetail> findAll() {
        return XQuery.getBeanList(OrderDetail.class, findAllSql);
    }

    @Override
    public OrderDetail findById(Long id) {
        return XQuery.getSingleBean(OrderDetail.class, findByIdSql, id);
    }

    @Override
    public void create(OrderDetail entity) {
        // Kiểm tra tồn kho
        String checkQuantitySql = "SELECT Quantity FROM Products WHERE ProductId = ?";
        ResultSet rs = XJdbc.executeQuery(checkQuantitySql, entity.getProductId());
        try {
            if (rs.next()) {
                int available = rs.getInt("Quantity");
                if (available < entity.getQuantity()) {
                    XDialog.alert(null, "Không đủ hàng tồn kho! Hiện còn " + available + " sản phẩm.");
                    return;
                }
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        // Thêm chi tiết đơn hàng
        Object[] values = {
            entity.getOrderId(),
            entity.getProductId(),
            entity.getUnitPrice(),
            entity.getDiscount(),
            entity.getQuantity(),
            entity.getColor() // thêm color vào insert
        };
        XJdbc.executeUpdate(createSql, values);

        // Trừ kho
        String sqlUpdateQuantity = "UPDATE Products SET Quantity = Quantity - ? WHERE ProductId = ?";
        XJdbc.executeUpdate(sqlUpdateQuantity, entity.getQuantity(), entity.getProductId());
    }
}
