package com.example.for_sber.crudforsbertestapplication.controller;

import com.example.for_sber.crudforsbertestapplication.dto.ProductDTO;
import com.example.for_sber.crudforsbertestapplication.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/product")
@RestController
public class ProductController {

    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO){
        return productService.save(productDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.delete(id);
    }
}
