package com.example.shoppinglist.repositorys;


import com.example.shoppinglist.models.ShoppingListItem;
import com.example.shoppinglist.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* Repository per l'entità ShoppingListItem.
*/
@Repository
public interface ShoppingListItemRepository extends JpaRepository<ShoppingListItem, Long> {
List<ShoppingListItem> findByUser(User user);
}
