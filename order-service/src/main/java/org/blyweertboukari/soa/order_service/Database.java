package org.blyweertboukari.soa.order_service;

import org.blyweertboukari.soa.order_service.dto.*;
import org.blyweertboukari.soa.order_service.models.Customer;
import org.blyweertboukari.soa.order_service.models.Item;
import org.blyweertboukari.soa.order_service.models.Order;
import org.blyweertboukari.soa.order_service.models.OrderItem;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Database {
    private static final RestTemplate restTemplate  = new RestTemplate();
    private static final String INVENTORY_SERVICE_URL = "http://localhost:8081";

    public static final HashMap<Integer, Customer> customers = new HashMap<>();
    public static final HashMap<Integer, Order> orders =  new HashMap<>();

    public static Customer getCustomer(int id) {
        return customers.get(id);
    }

    public static void deleteCustomer(int id) {
        customers.remove(id);
    }

    public static Customer addCustomer(CustomerCreationDto customerCreationDto) {
        Customer newCustomer = new Customer(customers.size()+1, customerCreationDto.getFirstName(), customerCreationDto.getLastName());
        customers.put(newCustomer.getId(), newCustomer);
        return newCustomer;
    }

    public static Customer updateCustomer(int id, CustomerUpdateDto customerUpdateDto) {
        Customer customer = customers.get(id);

        if (customer == null) {
            return null;
        }

        customer.setFirstName(customerUpdateDto.getFirstName());
        customer.setLastName(customerUpdateDto.getLastName());

        return customer;
    }

    public static Order getOrder(int id) {
        return orders.get(id);
    }

    public static void deleteOrder(int id) {
        orders.remove(id);
    }

    public static Order addOrder(OrderCreationDto orderCreationDto) {
        HashMap<Integer, Item> items = new HashMap<>();

        for (OrderCreationDto.OrderItemCreationDto orderItemCreationDto: orderCreationDto.getOrderItems()) {
            Item item = restTemplate.getForObject(INVENTORY_SERVICE_URL + "/items/" + orderItemCreationDto.getItemId(), Item.class);

            if (item == null || item.getQuantity() < orderItemCreationDto.getQuantity()) {
                return null;
            }

            items.put(item.getId(), item);
        }

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderCreationDto.OrderItemCreationDto orderItemCreationDto: orderCreationDto.getOrderItems()) {
            OrderItem orderItem = new OrderItem(orderItemCreationDto.getItemId(), orderItemCreationDto.getQuantity());
            orderItems.add(orderItem);

            Item item = items.get(orderItem.getItemId());
            ItemUpdateDto itemUpdateDto = new ItemUpdateDto(item.getLabel(), item.getQuantity() - orderItem.getQuantity());
            itemUpdateDto.setQuantity(orderItem.getQuantity());

            HttpEntity<ItemUpdateDto> httpEntity = new HttpEntity<>(itemUpdateDto);
            restTemplate.exchange(INVENTORY_SERVICE_URL + "/items/" + orderItemCreationDto.getItemId(), HttpMethod.PUT, httpEntity, Item.class);
        }

        Order newOrder = new Order(orders.size()+1, orderCreationDto.getCustomerId(), orderItems);

        orders.put(newOrder.getId(), newOrder);

        return newOrder;
    }

    public static Order updateOrder(int id, OrderUpdateDto orderUpdateDto) {
    }
}
