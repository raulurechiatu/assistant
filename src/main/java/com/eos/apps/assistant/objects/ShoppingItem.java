package com.eos.apps.assistant.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingItem {
    String shelfItemId;
    String id;
    float quantity;
    float price;

    public ShoppingItem(ShelfItem shelfItem) {
        this.shelfItemId = shelfItem.getId();
        this.quantity = shelfItem.getDefaultQuantity();
        this.price = shelfItem.getDefaultPrice();
    }
}
