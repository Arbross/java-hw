package org.example.dto.productdto;

import lombok.Data;

@Data
public class CreateProductDTO {
    private String name;
    private int price;
    private String description;
}
