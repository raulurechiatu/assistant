package com.eos.apps.assistant.controllers;

import com.eos.apps.assistant.objects.ShelfItem;
import com.eos.apps.assistant.services.ShelfItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/add")
    public ResponseEntity<Boolean> add(@RequestBody ShelfItem item) {
        return ResponseEntity.ok(shelfItemService.add(item));
    }

    @PostMapping("/edit")
    public ResponseEntity<ShelfItem> edit(@RequestBody ShelfItem item) {
        return ResponseEntity.ok(shelfItemService.edit(item));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(@RequestBody String id) {
        shelfItemService.delete(id);
        return ResponseEntity.ok(true);
    }
}
