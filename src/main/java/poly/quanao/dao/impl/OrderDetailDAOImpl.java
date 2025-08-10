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

    // INSERT
    private static final String CREATE_SQL =
        "INSERT INTO OrderDetails (OrderId, ProductId, UnitPrice, Discount, Quantity) " +
        "VALUES (?, ?, ?, ?, ?)";

    // UPDATE
    private static final String UPDATE_SQL =
        "UPDATE OrderDetails " +
        "SET OrderId=?, ProductId=?, UnitPrice=?, Discount=?, Quantity=? " +
        "WHERE OrderDetailId=?";

    // DELETE theo detailId
    private static final String SQL_DELETE_BY_DETAIL_ID =
        "DELETE FROM OrderDetails WHERE OrderDetailId = ?";

    // DELETE theo orderId
    private static final String SQL_DELETE_BY_ORDER_ID =
        "DELETE FROM OrderDetails WHERE OrderId = ?";

    // SELECTs
    private static final String FIND_ALL_SQL =
        "SELECT od.OrderDetailId, od.OrderId, od.ProductId, od.UnitPrice, od.Discount, od.Quantity, " +
        "       p.ProductName AS productName " +
        "FROM OrderDetails od " +
        "JOIN Products p ON p.ProductId = od.ProductId";

    private static final String FIND_BY_ID_SQL =
        "SELECT od.OrderDetailId, od.OrderId, od.ProductId, od.UnitPrice, od.Discount, od.Quantity, " +
        "       p.ProductName AS productName " +
        "FROM OrderDetails od " +
        "JOIN Products p ON p.ProductId = od.ProductId " +
        "WHERE od.OrderDetailId = ?";

    private static final String FIND_BY_ORDER_ID_SQL =
        "SELECT od.OrderDetailId, od.OrderId, od.ProductId, od.UnitPrice, od.Discount, od.Quantity, " +
        "       p.ProductName AS productName " +
        "FROM OrderDetails od " +
        "JOIN Products p ON p.ProductId = od.ProductId " +
        "WHERE od.OrderId = ?";

    private static final String FIND_BY_PRODUCT_ID_SQL =
        "SELECT od.OrderDetailId, od.OrderId, od.ProductId, od.UnitPrice, od.Discount, od.Quantity, " +
        "       p.ProductName AS productName " +
        "FROM OrderDetails od " +
        "JOIN Products p ON p.ProductId = od.ProductId " +
        "WHERE od.ProductId = ?";

    @Override
    public List<OrderDetail> findByOrderId(Long orderId) {
        return XQuery.getBeanList(OrderDetail.class, FIND_BY_ORDER_ID_SQL, orderId);
    }

    @Override
    public void update(OrderDetail entity) {
        Object[] values = {
            entity.getOrderId(),
            entity.getProductId(),
            entity.getUnitPrice(),
            entity.getDiscount(),
            entity.getQuantity(),
            entity.getOrderDetailId()
        };
        XJdbc.executeUpdate(UPDATE_SQL, values);
    }

    @Override
public void deleteById(Long orderDetailId) {
    if (orderDetailId == null) return;
    XJdbc.executeUpdate(SQL_DELETE_BY_DETAIL_ID, orderDetailId);
}

    // Cho OrderDAO gọi khi xóa đơn hàng
    public int deleteByOrderId(Long orderId) {
        if (orderId == null) return 0;
        return XJdbc.executeUpdate(SQL_DELETE_BY_ORDER_ID, orderId);
    }

    @Override
    public List<OrderDetail> findAll() {
        return XQuery.getBeanList(OrderDetail.class, FIND_ALL_SQL);
    }

    @Override
    public OrderDetail findById(Long id) {
        return XQuery.getSingleBean(OrderDetail.class, FIND_BY_ID_SQL, id);
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
        XJdbc.executeUpdate(CREATE_SQL,
            entity.getOrderId(),
            entity.getProductId(),
            entity.getUnitPrice(),
            entity.getDiscount(),
            entity.getQuantity()
        );

        // Trừ kho
        String sqlUpdateQuantity = "UPDATE Products SET Quantity = Quantity - ? WHERE ProductId = ?";
        XJdbc.executeUpdate(sqlUpdateQuantity, entity.getQuantity(), entity.getProductId());
    }

    @Override
    public List<OrderDetail> findByProductId(String productId) {
         return XQuery.getBeanList(OrderDetail.class, FIND_BY_PRODUCT_ID_SQL, productId);
    }
}
