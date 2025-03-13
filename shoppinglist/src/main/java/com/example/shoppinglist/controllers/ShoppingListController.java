package com.example.shoppinglist.controllers;

import com.example.shoppinglist.models.Product;
import com.example.shoppinglist.models.ShoppingListItem;
import com.example.shoppinglist.models.User;
import com.example.shoppinglist.services.ProductService;
import com.example.shoppinglist.services.ShoppingListItemService;
import com.example.shoppinglist.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Controller per la gestione della lista acquisti dell'utente.
 */
@Controller
@RequestMapping("/shopping-list")
@RequiredArgsConstructor
public class ShoppingListController {

    private final ShoppingListItemService shoppingListService;
    private final UserService userService;
    private final ProductService productService;

    /**
     * Visualizza la lista acquisti dell'utente autenticato.
     *
     * @param model       Model per passare i dati alla vista
     * @param userDetails Informazioni dell'utente autenticato
     * @return Nome del template Thymeleaf per la visualizzazione della lista
     *         acquisti
     */
    @GetMapping
    public String listUserShoppingList(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        // Recupera l'utente in base all'email (username)
        User user = userService.findByEmail(userDetails.getUsername());
        List<ShoppingListItem> shoppingList = shoppingListService.getShoppingListForUser(user);
        model.addAttribute("shoppingList", shoppingList);
        return "shoppinglist";
    }

    /**
     * Aggiunge un prodotto alla lista acquisti dell'utente autenticato.
     *
     * @param productId   ID del prodotto da aggiungere
     * @param userDetails Informazioni dell'utente autenticato
     * @return Redirect alla pagina della lista acquisti
     */
    @GetMapping("/add/{productId}")
    public String addProductToShoppingList(@PathVariable Long productId,
            @AuthenticationPrincipal UserDetails userDetails) {
        // Recupera l'utente corrente
        User user = userService.findByEmail(userDetails.getUsername());
        // Recupera il prodotto da aggiungere
        Product product = productService.getProductById(productId);
        // Crea un nuovo elemento della lista acquisti
        ShoppingListItem item = new ShoppingListItem();
        item.setUser(user);
        item.setProduct(product);
        // Salva l'elemento nel database
        shoppingListService.addItem(item);
        // Redirect alla pagina della lista acquisti (o ai prodotti, a seconda delle
        // preferenze)
        return "redirect:/shopping-list";
    }
}
