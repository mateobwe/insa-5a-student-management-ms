package org.blyweertboukari.soa.order_service.dto;

import java.util.List;

public class OrderCreationDto {
    public static class OrderItemCreationDto {
        int itemId;
        int quantity;

        public OrderItemCreationDto(int itemId, int quantity) {
            this.itemId = itemId;
            this.quantity = quantity;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    private int customerId;
    private List<OrderItemCreationDto> orderItems;

    public OrderCreationDto(int customerId, List<OrderItemCreationDto> orderItems) {
        this.customerId = customerId;
        this.orderItems = orderItems;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<OrderItemCreationDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemCreationDto> orderItems) {
        this.orderItems = orderItems;
    }
}
