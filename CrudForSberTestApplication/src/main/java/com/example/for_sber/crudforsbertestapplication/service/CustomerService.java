package com.example.for_sber.crudforsbertestapplication.service;

import com.example.for_sber.crudforsbertestapplication.dto.CustomerDTO;
import com.example.for_sber.crudforsbertestapplication.entity.Customer;
import com.example.for_sber.crudforsbertestapplication.mapper.CustomerMapper;
import com.example.for_sber.crudforsbertestapplication.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;


    public CustomerService(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public CustomerDTO save(CustomerDTO customerDTO){
        log.info("Saving customer: {}", customerDTO.getFirstName());
        Customer customer = customerMapper.convertToEntity(customerDTO);
        customerDTO.setId(customerRepository.save(customer).getId());
        return customerDTO;
    }

    @Transactional
    public void delete(Long id){
        log.info("Deleting customer with id: {}", id);
        customerRepository.deleteById(id);
    }

}
