package com.example.shoppinglist.services;

import com.example.shoppinglist.models.ShoppingListItem;
import com.example.shoppinglist.models.User;
import com.example.shoppinglist.repositorys.ShoppingListItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servizio per la gestione della lista acquisti.
 */
@Service
@RequiredArgsConstructor
public class ShoppingListItemService {

    private final ShoppingListItemRepository shoppingListRepository;

    /**
     * Restituisce la lista degli item della lista acquisti per un dato utente.
     *
     * @param user L'utente di cui ottenere la lista
     * @return Lista degli item della lista acquisti
     */
    public List<ShoppingListItem> getShoppingListForUser(User user) {
        return shoppingListRepository.findByUser(user);
    }

    /**
     * Aggiunge un nuovo item alla lista acquisti.
     *
     * @param item L'item da aggiungere
     * @return L'item salvato
     */
    public ShoppingListItem addItem(ShoppingListItem item) {
        return shoppingListRepository.save(item);
    }
}