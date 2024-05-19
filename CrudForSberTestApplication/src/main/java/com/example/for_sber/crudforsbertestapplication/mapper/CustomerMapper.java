package com.example.for_sber.crudforsbertestapplication.mapper;

import com.example.for_sber.crudforsbertestapplication.dto.CustomerDTO;
import com.example.for_sber.crudforsbertestapplication.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public Customer convertToEntity(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, Customer.class);
    }

    public CustomerDTO convertToDTO(Customer customer){
        return modelMapper.map(customer, CustomerDTO.class);
    }

    public List<CustomerDTO> convertToDTOList(List<Customer> customers){
        return customers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
