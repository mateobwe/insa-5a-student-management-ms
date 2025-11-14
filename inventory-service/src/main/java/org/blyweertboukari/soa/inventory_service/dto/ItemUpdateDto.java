package org.blyweertboukari.soa.inventory_service.dto;

public class ItemUpdateDto {
    private String label;
    private int quantity;

    public ItemUpdateDto(String label, int quantity) {
        this.quantity = quantity;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
