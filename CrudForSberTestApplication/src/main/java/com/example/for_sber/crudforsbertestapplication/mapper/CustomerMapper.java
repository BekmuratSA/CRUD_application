package com.example.for_sber.crudforsbertestapplication.mapper;

import com.example.for_sber.crudforsbertestapplication.dto.CustomerDTO;
import com.example.for_sber.crudforsbertestapplication.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper class for converting between Customer and CustomerDTO objects.
 */
@Component
public class CustomerMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    /**
     * Converts a CustomerDTO object to a Customer entity.
     *
     * @param customerDTO The CustomerDTO object to convert.
     * @return The corresponding Customer entity.
     */
    public Customer convertToEntity(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, Customer.class);
    }

    /**
     * Converts a Customer entity to a CustomerDTO object.
     *
     * @param customer The Customer entity to convert.
     * @return The corresponding CustomerDTO object.
     */
    public CustomerDTO convertToDTO(Customer customer){
        return modelMapper.map(customer, CustomerDTO.class);
    }

    /**
     * Converts a list of Customer entities to a list of CustomerDTO objects.
     *
     * @param customers The list of Customer entities to convert.
     * @return The corresponding list of CustomerDTO objects.
     */
    public List<CustomerDTO> convertToDTOList(List<Customer> customers){
        return customers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
