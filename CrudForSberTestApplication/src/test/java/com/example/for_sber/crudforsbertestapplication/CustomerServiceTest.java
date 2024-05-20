package com.example.for_sber.crudforsbertestapplication;

import com.example.for_sber.crudforsbertestapplication.dto.CustomerDTO;
import com.example.for_sber.crudforsbertestapplication.entity.Customer;
import com.example.for_sber.crudforsbertestapplication.repository.CustomerRepository;
import com.example.for_sber.crudforsbertestapplication.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for CustomerService.
 */
@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    /**
     * Clears the repository after each test.
     */
    @AfterEach
    void clear(){
        customerRepository.deleteAll();
    }

    /**
     * Test for saving CustomerDTO.
     */
    @Test
    void save_customerDTO_savedSuccessfully(){
        // Arrange
        CustomerDTO customerDTO = CustomerDTO.builder()
                .firstName("John")
                .lastName("Kim")
                .phoneNumber(123456789L)
                .build();

        // Act
        CustomerDTO savedCustomerDTO = customerService.save(customerDTO);

        customerDTO.setId(savedCustomerDTO.getId());
        // Assert
        assertEquals(customerDTO, savedCustomerDTO);
    }

    /**
     * Test for updating CustomerDTO.
     */
    @Test
    void update_customerDTO_savedSuccessfully(){
        // Arrange
        CustomerDTO customerDTO = CustomerDTO.builder()
                .firstName("John")
                .lastName("Kim")
                .phoneNumber(123456789L)
                .build();
        customerDTO.setId(customerService.save(customerDTO).getId());

        CustomerDTO updatedDTO = CustomerDTO.builder()
                .id(customerDTO.getId())
                .firstName("UpdatedFirstName")
                .lastName("UpdatedLastName")
                .phoneNumber(123456789L)
                .build();

        // Act
        CustomerDTO updatedCustomerDTO = customerService.update(updatedDTO);

        // Assert
        assertEquals("UpdatedFirstName", updatedCustomerDTO.getFirstName());
        assertEquals("UpdatedLastName", updatedCustomerDTO.getLastName());
        assertEquals(123456789L, updatedCustomerDTO.getPhoneNumber());
    }

    /**
     * Test for deleting CustomerDTO.
     */
    @Test
    void delete_customer_deletedSuccessfully() {
        // Arrange
        CustomerDTO customerDTO = CustomerDTO.builder()
                .firstName("John")
                .lastName("Kim")
                .phoneNumber(000000L)
                .build();
        customerDTO.setId(customerService.save(customerDTO).getId());
        Long id = customerDTO.getId();

        // Act
        customerService.delete(id);

        // Assert
        assertNull(customerRepository.findById(id).orElse(null));
    }

    /**
     * Test for getting all CustomerDTOs.
     */
    @Test
    void getAll_customers_returnedSuccessfully() {
        // Arrange
        Customer customer1 = Customer.builder().firstName("John").lastName("Doe").phoneNumber(123456788l).build();
        Customer customer2 = Customer.builder().firstName("Jane").lastName("Doe").phoneNumber(1234567890l).build();

        List<Customer> customers = Arrays.asList(customer1, customer2);
        customer1.setId(customerRepository.save(customers.get(0)).getId());
        customer2.setId(customerRepository.save(customers.get(1)).getId());

        // Act
        List<CustomerDTO> customerDTOs = customerService.getAll();

        // Assert
        assertEquals(2, customerDTOs.size());
        assertEquals("John", customerDTOs.get(0).getFirstName());
        assertEquals("Jane", customerDTOs.get(1).getFirstName());
    }

    /**
     * Test for getting CustomerDTO by ID.
     */
    @Test
    void getById_existingCustomerId_directorReturned() {
        // Arrange
        Customer customer= Customer.builder()
                .firstName("John")
                .lastName("Doe")
                .phoneNumber(123456781L)
                .build();
        customer.setId(customerRepository.save(customer).getId());

        // Act
        CustomerDTO customerDTO = customerService.getById(customer.getId());

        // Assert
        assertNotNull(customerDTO);
        assertEquals("John", customerDTO.getFirstName());
        assertEquals("Doe", customerDTO.getLastName());
        assertEquals(123456781L, customerDTO.getPhoneNumber());
    }

    /**
     * Test for getting CustomerDTO by non-existing ID.
     */
    @Test
    void getById_nonExistingCustomerId_entityNotFoundExceptionThrown() {
        // Arrange
        Long id = 1L;

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> customerService.getById(id));
    }
}
