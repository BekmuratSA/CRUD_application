package com.example.for_sber.crudforsbertestapplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a customer.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
@Builder
public class Customer {

    /**
     * The unique identifier of the customer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The first name of the customer.
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * The last name of the customer.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * The phone number of the customer.
     */
    @Column(name = "phone_number")
    private Long phoneNumber;
}
