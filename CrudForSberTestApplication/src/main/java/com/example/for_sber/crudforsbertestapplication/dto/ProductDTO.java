package com.example.for_sber.crudforsbertestapplication.dto;
import com.example.for_sber.crudforsbertestapplication.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class representing a product.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    /**
     * The unique identifier of the product.
     */
    private Long id;

    /**
     * The name of the product.
     */
    private String productName;

    /**
     * The price of the product.
     */
    private Long price;

    /**
     * The customer of the product.
     */
    private Customer customer;
}