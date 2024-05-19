package com.example.for_sber.crudforsbertestapplication.service;

import com.example.for_sber.crudforsbertestapplication.dto.ProductDTO;
import com.example.for_sber.crudforsbertestapplication.entity.Product;
import com.example.for_sber.crudforsbertestapplication.mapper.ProductMapper;
import com.example.for_sber.crudforsbertestapplication.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for managing Product entities.
 */
@Service
@Slf4j
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductService(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    /**
     * Saves a ProductDTO object as a Product entity.
     *
     * @param productDTO The ProductDTO object to save.
     * @return The saved ProductDTO object.
     */
    @Transactional
    public ProductDTO save(ProductDTO productDTO){
        log.info("Saving product: {}", productDTO.getProductName());
        Product product = productMapper.convertToEntity(productDTO);
        productDTO.setId(productRepository.save(product).getId());
        return productDTO;
    }

    /**
     * Updates a ProductDTO object as a Product entity.
     *
     * @param productDTO The ProductDTO object to update.
     * @return The updated ProductDTO object.
     */
    @Transactional
    public ProductDTO update(ProductDTO productDTO){
        log.info("Updating product with id: {}", productDTO.getId());
        Product existingProduct = productRepository.findById(productDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + productDTO.getId() + " not found"));
        existingProduct.setProductName(productDTO.getProductName());
        existingProduct.setCustomer(productDTO.getCustomer());
        existingProduct.setPrice(productDTO.getPrice());

        return productMapper.convertToDTO(productRepository.save(existingProduct));
    }

    /**
     * Deletes a Product entity by its ID.
     *
     * @param id The ID of the Product entity to delete.
     */
    @Transactional
    public void delete(Long id){
        log.info("Deleting product with id: {}", id);
        productRepository.deleteById(id);
    }

    /**
     * Retrieves a list of all ProductDTO objects.
     *
     * @return The list of ProductDTO objects.
     */
    @Transactional
    public List<ProductDTO> getAll(){
        log.info("Getting all products");
        List<Product> products = productRepository.findAll();
        return productMapper.convertToDTOList(products);
    }

    /**
     * Retrieves a ProductDTO object by its ID.
     *
     * @param id The ID of the Product entity to retrieve.
     * @return The ProductDTO object.
     */
    @Transactional
    public ProductDTO getById(Long id){
        log.info("Getting product with id: {}",id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found"));
        return productMapper.convertToDTO(product);
    }
}
