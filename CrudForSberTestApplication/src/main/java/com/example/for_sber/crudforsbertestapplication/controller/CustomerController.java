package com.example.for_sber.crudforsbertestapplication.controller;

import com.example.for_sber.crudforsbertestapplication.dto.CustomerDTO;
import com.example.for_sber.crudforsbertestapplication.service.CustomerService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/customer")
@RestController
public class CustomerController {

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.save(customerDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        customerService.delete(id);
    }
}
