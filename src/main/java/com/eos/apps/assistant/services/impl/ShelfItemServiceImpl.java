package com.eos.apps.assistant.services.impl;

import com.eos.apps.assistant.objects.ShelfItem;
import com.eos.apps.assistant.services.FirebaseService;
import com.eos.apps.assistant.services.ShelfItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ShelfItemServiceImpl implements ShelfItemService {
    private static final String COLLECTION_NAME="shelf_item";

    FirebaseService<ShelfItem> firebaseService;

    @Autowired
    public ShelfItemServiceImpl() {
        this.firebaseService = new FirebaseServiceImpl<>(ShelfItem.class, COLLECTION_NAME);
    }


    @Override
    public void getAll() {
        List<ShelfItem> items = new ArrayList<>();
        firebaseService.getAll();
    }

    @Override
    public boolean add(ShelfItem shelfItem) {
        String id = UUID.randomUUID().toString();
        return firebaseService.add(id, shelfItem);
    }

    @Override
    public ShelfItem edit(ShelfItem shelfItem) {
        return firebaseService.edit(shelfItem.getId(), shelfItem);
    }

    @Override
    public void delete(String id) {
        firebaseService.delete(id);
    }


}
