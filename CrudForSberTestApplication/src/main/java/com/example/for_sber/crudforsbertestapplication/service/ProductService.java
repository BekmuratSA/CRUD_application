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

@Service
@Slf4j
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductService(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductDTO save(ProductDTO productDTO){
        log.info("Saving product: {}", productDTO.getProductName());
        Product product = productMapper.convertToEntity(productDTO);
        productDTO.setId(productRepository.save(product).getId());
        return productDTO;
    }


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

    @Transactional
    public void delete(Long id){
        log.info("Deleting product with id: {}", id);
        productRepository.deleteById(id);
    }

    @Transactional
    public List<ProductDTO> getAll(){
        log.info("Getting all products");
        List<Product> products = productRepository.findAll();
        return productMapper.convertToDTOList(products);
    }

    @Transactional
    public ProductDTO getById(Long id){
        log.info("Getting product with id: {}",id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found"));
        return productMapper.convertToDTO(product);
    }
}
