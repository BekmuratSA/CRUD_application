package com.example.for_sber.crudforsbertestapplication.mapper;

import com.example.for_sber.crudforsbertestapplication.dto.ProductDTO;
import com.example.for_sber.crudforsbertestapplication.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper class for converting between Product and ProductDTO objects.
 */
@Component
public class ProductMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    /**
     * Converts a ProductDTO object to a Product entity.
     *
     * @param productDTO The ProductDTO object to convert.
     * @return The corresponding Product entity.
     */
    public Product convertToEntity(ProductDTO productDTO){
        return modelMapper.map(productDTO, Product.class);
    }

    /**
     * Converts a Product entity to a ProductDTO object.
     *
     * @param product The Product entity to convert.
     * @return The corresponding ProductDTO object.
     */
    public ProductDTO convertToDTO(Product product){
        return modelMapper.map(product, ProductDTO.class);
    }

    /**
     * Converts a list of Product entities to a list of ProductDTO objects.
     *
     * @param products The list of Product entities to convert.
     * @return The corresponding list of ProductDTO objects.
     */
    public List<ProductDTO> convertToDTOList(List<Product> products){
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
