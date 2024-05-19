package com.example.for_sber.crudforsbertestapplication.controller;

import com.example.for_sber.crudforsbertestapplication.dto.CustomerDTO;
import com.example.for_sber.crudforsbertestapplication.service.CustomerService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller for handling operations related to customers.
 */
@RequestMapping("/customer")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    /**
     * Constructor for CustomerController.
     *
     * @param customerService The service for customer operations.
     */
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Endpoint for creating a new customer.
     *
     * @param customerDTO The DTO representing the customer to be created.
     * @return The DTO of the created customer.
     */
    @PostMapping
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.save(customerDTO);
    }

    /**
     * Endpoint for updating an existing customer.
     *
     * @param customerDTO The DTO representing the updated customer information.
     * @return The DTO of the updated customer.
     */
    @PutMapping
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.update(customerDTO);
    }

    /**
     * Endpoint for deleting a customer by id.
     *
     * @param id The id of the customer to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id){
        customerService.delete(id);
    }

    /**
     * Endpoint for retrieving all customers.
     *
     * @return A list of DTOs representing all customers.
     */
    @GetMapping
    public List<CustomerDTO> getAllCustomer(){
        return customerService.getAll();
    }

    /**
     * Endpoint for retrieving a customer by id.
     *
     * @param id The id of the customer to retrieve.
     * @return The DTO representing the customer with the specified id.
     */
    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable Long id){
        return customerService.getById(id);
    }

}
