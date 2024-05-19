package com.example.for_sber.crudforsbertestapplication.dto;
import com.example.for_sber.crudforsbertestapplication.entity.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;

    private String product_name;

    private Long price;

    private Customer customer;
}