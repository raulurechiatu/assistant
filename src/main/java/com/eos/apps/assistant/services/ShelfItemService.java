package com.eos.apps.assistant.services;

import com.eos.apps.assistant.objects.ShelfItem;

import java.util.List;

public interface ShelfItemService {
    void getAll();
    boolean add(ShelfItem shelfItem);
    ShelfItem edit(ShelfItem shelfItem);
    void delete(String id);
}
