package com.example.shoppinglist.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Entità per rappresentare un utente del sistema.
 */
@Entity
@Table(name = "users")
@Data // Genera getter, setter, toString, equals, hashCode
@NoArgsConstructor // Genera costruttore senza argomenti
@AllArgsConstructor // Genera costruttore con tutti gli argomenti
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // La colonna email deve essere unica e non null
    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    // Mappatura one-to-many: un utente può avere più voci nella lista degli
    // acquisti
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShoppingListItem> shoppingList;
}
