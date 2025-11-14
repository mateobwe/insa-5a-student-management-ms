package org.blyweertboukari.soa.order_service.dto;

import java.util.List;

public class OrderUpdateDto {
    public static class OrderItemUpdateDto {
        int itemId;
        int quantity;

        public OrderItemUpdateDto(int itemId, int quantity) {
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

    private List<OrderItemUpdateDto> orderItems;

    public OrderUpdateDto(List<OrderItemUpdateDto> orderItems) {
        this.orderItems = orderItems;
    }

    public List<OrderItemUpdateDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemUpdateDto> orderItems) {
        this.orderItems = orderItems;
    }
}
