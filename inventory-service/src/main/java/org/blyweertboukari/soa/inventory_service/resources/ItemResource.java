package org.blyweertboukari.soa.inventory_service.resources;

import org.blyweertboukari.soa.inventory_service.Database;
import org.blyweertboukari.soa.inventory_service.dto.ItemCreationDto;
import org.blyweertboukari.soa.inventory_service.dto.ItemUpdateDto;
import org.blyweertboukari.soa.inventory_service.models.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/items")
public class ItemResource {

    @GetMapping
    public ResponseEntity<List<Item>> getItems() {
        return new ResponseEntity<>(Database.getItemsStock(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Item> create(@RequestBody ItemCreationDto itemCreationDto) {
        if (itemCreationDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (itemCreationDto.getQuantity() < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (itemCreationDto.getLabel() == null || "".equals(itemCreationDto.getLabel())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Item item = Database.addItem(itemCreationDto);

        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable int id) {
        Item item = Database.getItem(id);

        if (item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> update(@PathVariable int id, @RequestBody ItemUpdateDto itemUpdateDto) {
        if (
                (itemUpdateDto.getQuantity() < 0) &&
                        (itemUpdateDto.getLabel() == null || "".equals(itemUpdateDto.getLabel()))
        ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Item item = Database.getItem(id);

        if (item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Item updatedItem = Database.updateItem(item.getId(), itemUpdateDto);

        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Item> delete(@PathVariable int id) {
        Item item = Database.getItem(id);

        if (item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Database.deleteItem(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
