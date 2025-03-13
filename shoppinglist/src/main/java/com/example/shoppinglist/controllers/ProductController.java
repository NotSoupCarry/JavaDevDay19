package com.example.shoppinglist.controllers;

import com.example.shoppinglist.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller per la visualizzazione dei prodotti.
 */
@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * Visualizza la lista dei prodotti disponibili.
     *
     * @param model Model per passare i dati alla vista
     * @return Nome del template Thymeleaf per la visualizzazione dei prodotti
     */
    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }
}