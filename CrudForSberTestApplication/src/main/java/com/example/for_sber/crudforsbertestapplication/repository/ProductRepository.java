package com.example.for_sber.crudforsbertestapplication.repository;

import com.example.for_sber.crudforsbertestapplication.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
