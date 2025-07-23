package poly.quanao.entity;

import java.util.Date;
import lombok.Data;

@Data
public class Order {
    private int orderId;      // int
    private String username;  // nvarchar(20)
    private int cardId;       // int
    private Date orderDate;   // datetime
    private String status;    // nvarchar(50)
}
