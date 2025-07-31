package poly.quanao.dao.impl;

import java.util.List;
import poly.quanao.dao.OrderDetailDAO;
import poly.quanao.entity.OrderDetail;
import poly.quanao.util.XJdbc;
import poly.quanao.util.XQuery;


public class OrderDetailDAOImpl implements OrderDetailDAO {

    String createSql = 
    "INSERT INTO OrderDetails (OrderId, ProductId, UnitPrice, Discount, Quantity) VALUES (?, ?, ?, ?, ?)";

String updateSql = 
    "UPDATE OrderDetails SET OrderId=?, ProductId=?, UnitPrice=?, Discount=?, Quantity=? WHERE OrderDetailId=?";

String deleteSql = 
    "DELETE FROM OrderDetails WHERE OrderDetailId=?";

String findAllSql = 
    "SELECT od.OrderDetailId, od.OrderId, od.ProductId, od.UnitPrice, od.Discount, od.Quantity, " +
    "p.ProductName AS productName " +
    "FROM OrderDetails od " +
    "JOIN Products p ON p.ProductId = od.ProductId";


String findByIdSql = 
    "SELECT od.OrderDetailId, od.OrderId, od.ProductId, od.UnitPrice, od.Discount, od.Quantity, " +
    "p.ProductName AS productName " +
    "FROM OrderDetails od " +
    "JOIN Products p ON p.ProductId = od.ProductId " +
    "WHERE od.OrderDetailId=?";


String findByOrderIdSql = 
    "SELECT od.OrderDetailId, od.OrderId, od.ProductId, od.UnitPrice, od.Discount, od.Quantity, " +
    "p.ProductName AS productName " +
    "FROM OrderDetails od " +
    "JOIN Products p ON p.ProductId = od.ProductId " +
    "WHERE od.OrderId=?";


String findByProductIdSql = 
    "SELECT od.OrderDetailId, od.OrderId, od.ProductId, od.UnitPrice, od.Discount, od.Quantity, " +
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
            entity.getOrderDetailId()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

   @Override
    public void deleteById(Long id) {
    // Xóa các chi tiết hóa đơn trước
    String deleteOrderDetailsSql = "DELETE FROM OrderDetails WHERE OrderId = ?";
    XJdbc.executeUpdate(deleteOrderDetailsSql, id);

    // Sau đó xóa hóa đơn chính
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
        Object[] values = {
            entity.getOrderId(),
            entity.getProductId(),
            entity.getUnitPrice(),
            entity.getDiscount(),
            entity.getQuantity()
        };
        XJdbc.executeUpdate(createSql, values);}

}