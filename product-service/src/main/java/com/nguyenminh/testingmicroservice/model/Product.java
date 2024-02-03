package com.nguyenminh.testingmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder // simplifies object creation and improves code readability
@Data //@getter , @setter , @@ToString , @EqualsAndHashCode .....
public class Product {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;

}
