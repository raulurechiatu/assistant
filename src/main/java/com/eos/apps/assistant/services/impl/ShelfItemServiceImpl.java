package com.eos.apps.assistant.services.impl;

import com.eos.apps.assistant.objects.ShelfItem;
import com.eos.apps.assistant.services.FirebaseService;
import com.eos.apps.assistant.services.ShelfItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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


}
