package com.eos.apps.assistant.controllers;

import com.eos.apps.assistant.objects.ShelfItem;
import com.eos.apps.assistant.services.ShelfItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/items")
public class ShelfItemController {

    @Autowired
    ShelfItemService shelfItemService;

    @RequestMapping("/all")
    public ResponseEntity<Boolean> getShelfItems() {
        shelfItemService.getAll();
        return ResponseEntity.ok(true);
    }
}
