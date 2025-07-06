package poly.quanao.util;

import poly.quanao.entity.User;

public class XAuth {
    public static User user = User.builder()
        .username("admin")
        .password("123")
        .fullName("Quản trị viên")
        .role("admin")
        .build();
}
