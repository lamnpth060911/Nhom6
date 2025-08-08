package poly.quanao.entity;

import java.util.Date;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Order {
    private Long orderId;      // int
    private String username;  // nvarchar(20)
    @Builder.Default
    private Date checkin = new Date();
    private Date checkout;
    private int status;
    private String color;
    public enum Status {
    Servicing, Completed, Canceled;
    }// nvarchar(50)
}
