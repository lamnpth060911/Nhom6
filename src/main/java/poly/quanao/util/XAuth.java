
import poly.quanao.entity.User;

public class XAuth {
    public static User user;

    static {
        user = new User();
        user.setUsername("user1@gmail.com");
        user.setPassword("123");
        user.setFullName("Tên người dùng");
        user.setRole("admin"); // hoặc "staff"
    }
}
