package poly.quanao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Products {
    private int productId;
    private String productName;
    private double price;
    private double discount;
    private boolean inStock;
    private String color;
    private String imagePath;
    private String categoryId;  // ✅ sửa sang int
}
