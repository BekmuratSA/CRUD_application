package com.example.for_sber.crudforsbertestapplication.controller;

import com.example.for_sber.crudforsbertestapplication.dto.ProductDTO;
import com.example.for_sber.crudforsbertestapplication.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling operations related to products.
 */
@RequestMapping("/product")
@RestController
public class ProductController {

    private final ProductService productService;

    /**
     * Constructor for ProductController.
     *
     * @param productService The service for product operations.
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Endpoint for creating a new product.
     *
     * @param productDTO The DTO representing the product to be created.
     * @return The DTO of the product.
     */
    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO){
        return productService.save(productDTO);
    }

    /**
     * Endpoint for updating an existing product.
     *
     * @param productDTO The DTO representing the updated product information.
     * @return The DTO of the updated product.
     */
    @PutMapping
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO){
        return productService.update(productDTO);
    }

    /**
     * Endpoint for deleting a product by id.
     *
     * @param id The id of the product to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.delete(id);
    }

    /**
     * Endpoint for retrieving all products.
     *
     * @return A list of DTOs representing all products.
     */
    @GetMapping
    public List<ProductDTO> getAllProduct(){
        return productService.getAll();
    }

    /**
     * Endpoint for retrieving a product by id.
     *
     * @param id The id of the product to retrieve.
     * @return The DTO representing the product with the specified id.
     */
    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Long id){
        return productService.getById(id);
    }

}
