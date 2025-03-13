package com.example.shoppinglist.models;

import jakarta.persistence.*;
import lombok.*;


/**
* Entità per rappresentare un prodotto disponibile.
*/
@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;
}