package com.eos.apps.assistant.services;

import com.eos.apps.assistant.objects.ShelfItem;
import com.eos.apps.assistant.objects.ShoppingItem;

public interface ShoppingItemService {
    void getAll();
    boolean add(ShoppingItem shelfItem);

    boolean add(ShelfItem shelfItem);

    ShoppingItem edit(ShoppingItem shelfItem);
    void delete(String id);
}
