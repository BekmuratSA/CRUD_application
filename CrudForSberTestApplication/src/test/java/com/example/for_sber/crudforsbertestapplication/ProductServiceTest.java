package com.example.for_sber.crudforsbertestapplication;

import com.example.for_sber.crudforsbertestapplication.dto.ProductDTO;
import com.example.for_sber.crudforsbertestapplication.entity.Customer;
import com.example.for_sber.crudforsbertestapplication.repository.CustomerRepository;
import com.example.for_sber.crudforsbertestapplication.repository.ProductRepository;
import com.example.for_sber.crudforsbertestapplication.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ProductServiceTest.
 */
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductService productService;

    @AfterEach
    void clear() {
        productRepository.deleteAll();
        customerRepository.deleteAll();
    }

    /**
     * Tests the save method of the ProductService class.
     */
    @Test
    void save_productDTO_savedSuccessfully() {
        // Arrange
        Customer customer = Customer.builder()
                .firstName("Tom")
                .lastName("Pak")
                .phoneNumber(1234237L)
                .build();
        customer.setId(customerRepository.save(customer).getId());

        ProductDTO productDTO = ProductDTO.builder()
                .productName("testName")
                .price(34L)
                .customer(customer)
                .build();

        // Act
        ProductDTO savedProductDTO = productService.save(productDTO);
        productDTO.setId(savedProductDTO.getId());

        // Assert
        assertEquals(productDTO, savedProductDTO);
    }

    /**
     * Tests the update method of the ProductService class.
     */
    @Test
    void update_productDTO_updatedSuccessfully() {
        // Arrange
        Customer customer = Customer.builder()
                .firstName("Tom")
                .lastName("Pak")
                .phoneNumber(37L)
                .build();
        customer.setId(customerRepository.save(customer).getId());

        ProductDTO productDTO = ProductDTO.builder()
                .productName("testName")
                .price(234L)
                .customer(customer)
                .build();

        productService.save(productDTO);

        ProductDTO newProductDTO = ProductDTO.builder()
                .id(productDTO.getId())
                .productName("changedName")
                .price(2000L)
                .customer(customer)
                .build();

        // Act
        ProductDTO updatedProductDTO = productService.update(newProductDTO);

        // Assert
        assertEquals("changedName", updatedProductDTO.getProductName());
        assertEquals(2000L, updatedProductDTO.getPrice());
    }

    /**
     * Tests the delete method of the ProductService class.
     */
    @Test
    void delete_product_deletedSuccessfully() {
        // Arrange
        Customer customer = Customer.builder()
                .firstName("Tom")
                .lastName("Pak")
                .phoneNumber(37L)
                .build();
        customer.setId(customerRepository.save(customer).getId());

        ProductDTO productDTO = ProductDTO.builder()
                .productName("testName")
                .price(199L)
                .customer(customer)
                .build();

        productService.save(productDTO);

        // Act
        productService.delete(productDTO.getId());

        // Assert
        assertNull(productRepository.findById(productDTO.getId()).orElse(null));
    }

    /**
     * Tests the getAll method of the ProductService class.
     */
    @Test
    void getAll_movies_returnedSuccessfully() {
        // Arrange
        Customer customer1 = Customer.builder()
                .firstName("Tom")
                .lastName("Pak")
                .phoneNumber(37L)
                .build();
        customer1.setId(customerRepository.save(customer1).getId());

        ProductDTO productDTO1 = ProductDTO.builder()
                .productName("testName1")
                .price(199L)
                .customer(customer1)
                .build();
        productService.save(productDTO1);

        Customer customer2 = Customer.builder()
                .firstName("Jane")
                .lastName("Pak")
                .phoneNumber(39L)
                .build();
        customer2.setId(customerRepository.save(customer2).getId());

        ProductDTO productDTO2 = ProductDTO.builder()
                .productName("testName2")
                .price(199L)
                .customer(customer2)
                .build();
        productService.save(productDTO2);

        // Act
        List<ProductDTO> productDTOS = productService.getAll();

        // Assert
        assertEquals(2, productDTOS.size());
        assertEquals("testName1", productDTOS.get(0).getProductName());
        assertEquals("testName2", productDTOS.get(1).getProductName());
    }

    /**
     * Tests the getById method of the ProductService class with an existing product ID.
     */
    @Test
    void getById_existingProductId_movieReturned() {
        // Arrange
        Customer customer = Customer.builder()
                .firstName("Tom")
                .lastName("Pak")
                .phoneNumber(37L)
                .build();
        customer.setId(customerRepository.save(customer).getId());

        ProductDTO productDTO = ProductDTO.builder()
                .productName("testName")
                .price(190L)
                .customer(customer)
                .build();

        productService.save(productDTO);

        // Act
        ProductDTO result = productService.getById(productDTO.getId());

        // Assert
        assertNotNull(result);
        assertEquals("testName", result.getProductName());
        assertEquals(190, result.getPrice());
    }

    /**
     * Tests the getById method of the ProductService class with a non-existing product ID.
     */
    @Test
    void getById_nonExistingProductId_entityNotFoundExceptionThrown() {
        // Arrange
        Long id = 1L;

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> productService.getById(id));
    }
    
}
