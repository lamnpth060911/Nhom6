package poly.quanao.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class XJdbc {
    public static Connection openConnection() {
        try {
            var driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            var dburl = "jdbc:sqlserver://localhost:1433;database=PolyCafe;encrypt=true;trustServerCertificate=true;";
            var username = "sa";
            var password = "123"; // Đổi thành pass thật của bạn

            Class.forName(driver);
            return DriverManager.getConnection(dburl, username, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void executeUpdate(String sql, Object... args) {
        try (
            Connection con = openConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
        ) {
            for (int i = 0; i < args.length; i++) {
                stmt.setObject(i + 1, args[i]);
            }
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet executeQuery(String sql, Object... args) {
        try {
            Connection con = openConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                stmt.setObject(i + 1, args[i]);
            }
            return stmt.executeQuery();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static <T> List<T> query(String sql, ResultSetHandler<T> handler, Object... args) {
    List<T> list = new ArrayList<>();
    try (
        Connection con = openConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
    ) {
        for (int i = 0; i < args.length; i++) {
            stmt.setObject(i + 1, args[i]);
        }

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            list.add(handler.handle(rs));
        }
        rs.close();
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    return list;
}

    @FunctionalInterface
    public interface ResultSetHandler<T> {
        T handle(ResultSet rs) throws SQLException;
    }

}
