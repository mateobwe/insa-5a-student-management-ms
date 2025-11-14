package org.blyweertboukari.soa.order_service.models;

public class Item {
    private int id;
    private String label;
    private int quantity;

    public Item(int id, String label, int quantity) {
        this.id = id;
        this.quantity = quantity;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
