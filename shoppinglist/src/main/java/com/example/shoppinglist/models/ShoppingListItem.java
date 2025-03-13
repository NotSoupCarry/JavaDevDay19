package com.example.shoppinglist.models;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entità per rappresentare un elemento della lista acquisti di un utente.
 */
@Entity
@Table(name = "shopping_list")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Associazione Many-to-One: molti elementi possono appartenere a un utente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // Associazione Many-to-One: molti elementi possono riferirsi a un prodotto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}