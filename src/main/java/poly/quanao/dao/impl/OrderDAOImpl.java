package poly.quanao.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import poly.quanao.dao.OrderDAO;
import poly.quanao.entity.Order;
import poly.quanao.util.XJdbc;
import poly.quanao.util.XAuth;

public class OrderDAOImpl implements OrderDAO {

    String insertSql = "INSERT INTO Bills(Username, CardId, OrderDate, Status) VALUES(?, ?, ?, ?)";
    String updateSql = "UPDATE Bills SET Username=?, CardId=?, OrderDate=?, Status=? WHERE OrderId=?";
    String deleteSql = "DELETE FROM Bills WHERE OrderId=?";
    String findAllSql = "SELECT * FROM Bills";
    String findByIdSql = "SELECT * FROM Bills WHERE OrderId=?";
    String findByUsernameSql = "SELECT * FROM Bills WHERE Username=?";
    String findByCardIdSql = "SELECT * FROM Bills WHERE CardId=?";
    String findByTimeRangeSql = "SELECT * FROM Bills WHERE OrderDate BETWEEN ? AND ? ORDER BY OrderDate DESC";

    // ✅ Map ResultSet -> Order
    private Order mapResultSet(ResultSet rs) throws Exception {
        Order o = new Order();
        o.setOrderId(rs.getInt("OrderId"));
        o.setUsername(rs.getString("Username"));
        o.setCardId(rs.getInt("CardId"));
        o.setOrderDate(rs.getTimestamp("OrderDate"));
        o.setStatus(rs.getString("Status"));
        return o;
    }

    @Override
    public void create(Order entity) {
        XJdbc.executeUpdate(insertSql,
            entity.getUsername(),
            entity.getCardId(),
            entity.getOrderDate(),
            entity.getStatus()
        );
    }

    @Override
    public void update(Order entity) {
        XJdbc.executeUpdate(updateSql,
            entity.getUsername(),
            entity.getCardId(),
            entity.getOrderDate(),
            entity.getStatus(),
            entity.getOrderId()
        );
    }

    @Override
    public void deleteById(Long id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<Order> findAll() {
        List<Order> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(findAllSql)) {
            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi findAll Bills", e);
        }
        return list;
    }

    @Override
    public Order findById(Long id) {
        try (ResultSet rs = XJdbc.executeQuery(findByIdSql, id)) {
            if (rs.next()) {
                return mapResultSet(rs);
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi findById Bill", e);
        }
        return null;
    }

    public List<Order> findByUsername(String username) {
        List<Order> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(findByUsernameSql, username)) {
            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi findByUsername", e);
        }
        return list;
    }

    public List<Order> findByCardId(Integer cardId) {
        List<Order> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(findByCardIdSql, cardId)) {
            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi findByCardId", e);
        }
        return list;
    }

    public List<Order> findByTimeRange(Date begin, Date end) {
        List<Order> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(findByTimeRangeSql, begin, end)) {
            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi findByTimeRange", e);
        }
        return list;
    }

    // ✅ Tìm Order đang phục vụ (Status='Đang phục vụ')
    public Order findServicingByCardId(Integer cardId) {
        String sql = "SELECT * FROM Bills WHERE CardId=? AND Status=N'Đang phục vụ'";
        Order order = null;

        try (ResultSet rs = XJdbc.executeQuery(sql, cardId)) {
            if (rs.next()) {
                order = mapResultSet(rs);
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi findServicingByCardId", e);
        }

        if (order == null) {
            // Không có -> tạo mới
            Order newOrder = new Order();
            newOrder.setCardId(cardId);
            newOrder.setOrderDate(new Date());
            newOrder.setStatus("Đang phục vụ");
            newOrder.setUsername(XAuth.user.getUsername());

            this.create(newOrder);

            // Lấy lại bill mới nhất
            try (ResultSet rs = XJdbc.executeQuery(sql, cardId)) {
                if (rs.next()) {
                    order = mapResultSet(rs);
                }
            } catch (Exception e) {
                throw new RuntimeException("Lỗi lấy lại Order mới", e);
            }
        }
        return order;
    }

    @Override
public List<Order> findByUserAndTimeRange(String username, Date begin, Date end) {
    String sql = "SELECT * FROM Bills WHERE Username=? AND OrderDate BETWEEN ? AND ? ORDER BY OrderDate DESC";
    List<Order> list = new ArrayList<>();

    try (ResultSet rs = XJdbc.executeQuery(sql, username, begin, end)) {
        while (rs.next()) {
            list.add(mapResultSet(rs));  // ✅ dùng hàm mapResultSet() đã viết sẵn
        }
    } catch (Exception e) {
        throw new RuntimeException("Lỗi findByUserAndTimeRange", e);
    }
    return list;
}

}
