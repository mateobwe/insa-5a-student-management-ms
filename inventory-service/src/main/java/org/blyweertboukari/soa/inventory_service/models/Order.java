package org.blyweertboukari.soa.inventory_service.models;

import java.util.List;

public class Order {
    private int id;
    private int customerId;
    private List<OrderItem> orderItems;

    public Order(int id, int customerId, List<OrderItem> orderItems) {
        this.id = id;
        this.customerId = customerId;
        this.orderItems = orderItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
