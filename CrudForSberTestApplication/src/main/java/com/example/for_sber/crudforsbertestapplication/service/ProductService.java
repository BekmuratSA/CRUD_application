package com.example.for_sber.crudforsbertestapplication.service;

import com.example.for_sber.crudforsbertestapplication.dto.ProductDTO;
import com.example.for_sber.crudforsbertestapplication.entity.Product;
import com.example.for_sber.crudforsbertestapplication.mapper.ProductMapper;
import com.example.for_sber.crudforsbertestapplication.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
    public void delete(Long id){
        log.info("Deleting product with id: {}", id);
        productRepository.deleteById(id);
    }

}
