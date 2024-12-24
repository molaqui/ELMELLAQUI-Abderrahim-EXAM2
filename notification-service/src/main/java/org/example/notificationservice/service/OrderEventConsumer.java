package org.example.notificationservice.service;


import org.example.notificationservice.dto.OrderEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class OrderEventConsumer {




    @Bean
    public Consumer<OrderEvent> EventCommande() {
        return (input) -> {
            System.out.println("***********************");
            System.out.println(input.toString());
            System.out.println("***********************");
        };
    }


}
