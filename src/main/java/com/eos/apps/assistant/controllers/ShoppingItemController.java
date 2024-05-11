package com.eos.apps.assistant.controllers;

import com.eos.apps.assistant.objects.ShelfItem;
import com.eos.apps.assistant.services.ShelfItemService;
import com.eos.apps.assistant.services.ShoppingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/shopping")
public class ShoppingItemController {

    @Autowired
    ShoppingItemService shoppingItemService;

    @RequestMapping("/all")
    public ResponseEntity<Boolean> getShelfItems() {
        shoppingItemService.getAll();
        return ResponseEntity.ok(true);
    }

    @PostMapping("/add")
    public ResponseEntity<Boolean> add(@RequestBody ShelfItem item) {
        return ResponseEntity.ok(shoppingItemService.add(item));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(@RequestBody String id) {
        shoppingItemService.delete(id);
        return ResponseEntity.ok(true);
    }
}
