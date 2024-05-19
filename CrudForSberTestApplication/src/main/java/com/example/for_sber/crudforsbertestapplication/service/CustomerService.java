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

/**
 * Service class for managing Customer entity.
 */
@Service
@Slf4j
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;


    public CustomerService(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    /**
     * Saves a CustomerDTO object as a Customer entity.
     *
     * @param customerDTO The CustomerDTO object to save.
     * @return The saved CustomerDTO object.
     */
    @Transactional
    public CustomerDTO save(CustomerDTO customerDTO){
        log.info("Saving customer: {}", customerDTO.getFirstName());
        Customer customer = customerMapper.convertToEntity(customerDTO);
        customerDTO.setId(customerRepository.save(customer).getId());
        return customerDTO;
    }

    /**
     * Updates a CustomerDTO object as a Customer entity.
     *
     * @param customerDTO The CustomerDTO object to update.
     * @return The updated CustomerDTO object.
     */
    @Transactional
    public CustomerDTO update(CustomerDTO customerDTO){
        log.info("Updating customer with id: {}", customerDTO.getId());
        Customer existingCustomer = customerRepository.findById(customerDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Customer with id " + customerDTO.getId() + " not found"));
        existingCustomer.setFirstName(customerDTO.getFirstName());
        existingCustomer.setLastName(customerDTO.getLastName());
        existingCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
        return customerMapper.convertToDTO(customerRepository.save(existingCustomer));
    }

    /**
     * Deletes a Customer entity by its ID.
     *
     * @param id The ID of the Customer entity to delete.
     */
    @Transactional
    public void delete(Long id){
        log.info("Deleting customer with id: {}", id);
        customerRepository.deleteById(id);
    }

    /**
     * Retrieves a list of all CustomerDTO objects.
     *
     * @return The list of CustomerDTO objects.
     */
    @Transactional
    public List<CustomerDTO> getAll(){
        log.info("Getting all customers");
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.convertToDTOList(customers);
    }

    /**
     * Retrieves a CustomerDTO object by its ID.
     *
     * @param id The ID of the Customer entity to retrieve.
     * @return The CustomerDTO object.
     */
    @Transactional
    public CustomerDTO getById(Long id){
        log.info("Getting customers with id: {}", id);
        return customerRepository.findById(id)
                .map(customerMapper::convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Customer with id " + id + " not found"));
    }
}
