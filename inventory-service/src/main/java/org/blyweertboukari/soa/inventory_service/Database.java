package org.blyweertboukari.soa.inventory_service;

import org.blyweertboukari.soa.inventory_service.dto.ItemCreationDto;
import org.blyweertboukari.soa.inventory_service.dto.ItemUpdateDto;
import org.blyweertboukari.soa.inventory_service.models.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Database {
    public static final HashMap<Integer, Item> itemsStock = new HashMap<>();

    public static Item getItem(int id) {
        return itemsStock.get(id);
    }

    public static List<Item> getItemsStock() {
        return new ArrayList<Item>(itemsStock.values());
    }

    public static void deleteItem(int id) {
        itemsStock.remove(id);
    }

    public static Item addItem(ItemCreationDto itemCreationDto) {
        Item newItem = new Item(itemsStock.size()+1, itemCreationDto.getLabel(), itemCreationDto.getQuantity());
        itemsStock.put(newItem.getId(), newItem);
        return newItem;
    }

    public static Item updateItem(int id, ItemUpdateDto itemUpdateDto) {
        Item item = itemsStock.get(id);

        if (item == null) {
            return null;
        }

        item.setLabel(itemUpdateDto.getLabel());
        item.setQuantity(itemUpdateDto.getQuantity());

        return item;
    }
}
