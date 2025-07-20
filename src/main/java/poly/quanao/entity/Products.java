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
    private String id;
    private String name;

    @Builder.Default
    private String image = "product.png";

    private double unitPrice;
    private double discount;
    private boolean InStock;
    private String categoryId;

    private String color; // Added to support color field in DB
}