package com.example.for_sber.crudforsbertestapplication.mapper;

import com.example.for_sber.crudforsbertestapplication.dto.ProductDTO;
import com.example.for_sber.crudforsbertestapplication.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public Product convertToEntity(ProductDTO productDTO){
        return modelMapper.map(productDTO, Product.class);
    }

    public ProductDTO convertToDTO(Product product){
        return modelMapper.map(product, ProductDTO.class);
    }

    public List<ProductDTO> convertToDTOList(List<Product> products){
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
