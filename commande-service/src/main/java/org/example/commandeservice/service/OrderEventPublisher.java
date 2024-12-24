package org.example.commandeservice.service;

import org.example.commandeservice.dto.OrderEvent;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class OrderEventPublisher {

    private final StreamBridge streamBridge;

    public OrderEventPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void sendOrderCreatedEvent(OrderEvent event) {
        streamBridge.send("order-out", event);
        System.out.println("Order event published: " + event);
    }
}
