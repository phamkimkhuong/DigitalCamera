package com.example.digitalcamerastore.repositories;

import com.example.digitalcamerastore.entities.Product;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByCode(@NotNull(message = "Code is required") String code);
}
