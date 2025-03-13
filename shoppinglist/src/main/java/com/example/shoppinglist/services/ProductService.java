package com.example.shoppinglist.services;

import com.example.shoppinglist.models.Product;
import com.example.shoppinglist.repositorys.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servizio per la gestione dei prodotti.
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * Restituisce la lista di tutti i prodotti disponibili.
     *
     * @return Lista dei prodotti
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Recupera un prodotto in base al suo ID.
     *
     * @param id L'ID del prodotto
     * @return Il prodotto trovato
     * @throws RuntimeException se il prodotto non viene trovato
     */
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prodotto non trovato con id: " + id));
    }
}
