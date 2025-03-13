package com.example.shoppinglist.controllers;

import com.example.shoppinglist.models.Product;
import com.example.shoppinglist.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductService productService;

    /**
     * Mostra il form per l'aggiunta di un nuovo prodotto.
     */
    @GetMapping("/products/new")
    public String showNewProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "new-product"; // Template Thymeleaf per il form
    }

    /**
     * Gestisce il salvataggio di un nuovo prodotto.
     */
    @PostMapping("/products")
    public String addProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products";
    }
}