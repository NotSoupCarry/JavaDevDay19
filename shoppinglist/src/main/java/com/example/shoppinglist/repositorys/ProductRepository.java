package com.example.shoppinglist.repositorys;

import com.example.shoppinglist.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository per l'entità Product.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}