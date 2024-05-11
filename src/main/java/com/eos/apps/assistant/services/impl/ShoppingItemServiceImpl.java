package com.eos.apps.assistant.services.impl;

import com.eos.apps.assistant.objects.ShelfItem;
import com.eos.apps.assistant.objects.ShoppingItem;
import com.eos.apps.assistant.services.FirebaseService;
import com.eos.apps.assistant.services.ShoppingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ShoppingItemServiceImpl implements ShoppingItemService {
    private static final String COLLECTION_NAME="shopping_item";

    FirebaseService<ShoppingItem> firebaseService;

    @Autowired
    public ShoppingItemServiceImpl() {
        this.firebaseService = new FirebaseServiceImpl<>(ShoppingItem.class, COLLECTION_NAME);
    }


    @Override
    public void getAll() {
        List<ShoppingItem> items = new ArrayList<>();
        firebaseService.getAll();
    }

    @Override
    public boolean add(ShoppingItem shoppingItem) {
        String id = UUID.randomUUID().toString();
        return firebaseService.add(id, shoppingItem);
    }

    @Override
    public boolean add(ShelfItem shelfItem) {
        ShoppingItem shoppingItem = new ShoppingItem(shelfItem);
        String id = UUID.randomUUID().toString();
        shoppingItem.setId(id);
        return firebaseService.add(id, shoppingItem);
    }

    @Override
    public ShoppingItem edit(ShoppingItem shoppingItem) {
        return firebaseService.edit(shoppingItem.getId(), shoppingItem);
    }

    @Override
    public void delete(String id) {
        firebaseService.delete(id);
    }


}
