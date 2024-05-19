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
    public CustomerDTO update(CustomerDTO customerDTO){
        log.info("Updating customer with id: {}", customerDTO.getId());
        Customer existingCustomer = customerRepository.findById(customerDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Customer with id " + customerDTO.getId() + " not found"));
        existingCustomer.setFirstName(customerDTO.getFirstName());
        existingCustomer.setLastName(customerDTO.getLastName());
        return customerMapper.convertToDTO(customerRepository.save(existingCustomer));
    }

    @Transactional
    public void delete(Long id){
        log.info("Deleting customer with id: {}", id);
        customerRepository.deleteById(id);
    }

    @Transactional
    public List<CustomerDTO> getAll(){
        log.info("Getting all customers");
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.convertToDTOList(customers);
    }

    @Transactional
    public CustomerDTO getById(Long id){
        log.info("Getting customers with id: {}", id);
        return customerRepository.findById(id)
                .map(customerMapper::convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Customer with id " + id + " not found"));
    }
}
