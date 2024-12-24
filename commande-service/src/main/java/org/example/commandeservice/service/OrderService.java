package org.example.commandeservice.service;

import lombok.AllArgsConstructor;
import org.example.commandeservice.dto.OrderEvent;
import org.example.commandeservice.entity.Orders;
import org.example.commandeservice.feignGraphQL.ProductGraphQLClient;
import org.example.commandeservice.repository.OrderRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderEventPublisher orderEventPublisher;
    private final ProductGraphQLClient productGraphQLClient;

    public String createOrder(Long productId, int quantity) {
        // Consulter le produit via GraphQL
        Map<String, Object> response = getProductById(productId);
        Map<String, Object> productData = (Map<String, Object>) response.get("data");

        if (productData == null || !productData.containsKey("getProductById")) {
            throw new RuntimeException("Produit non trouvé !");
        }

        Map<String, Object> product = (Map<String, Object>) productData.get("getProductById");
        int stock = (int) product.get("stock");

        if (stock < quantity) {
            throw new RuntimeException("Stock insuffisant !");
        }

        // Créer la commande
        Orders order = Orders.builder()
                .productId(productId)
                .quantity(quantity)
                .status("CREATED")
                .build();
        orderRepository.save(order);

        // Publier l'événement
        OrderEvent orderEvent = new OrderEvent(order.getId(), productId, quantity, "CREATED");
        orderEventPublisher.sendOrderCreatedEvent(orderEvent);

        return "Commande créée avec succès !";
    }

    private Map<String, Object> getProductById(Long productId) {
        Map<String, Object> query = new HashMap<>();
        query.put("query", "query { getProductById(id: " + productId + ") { id name stock } }");
        return productGraphQLClient.executeGraphQLQuery(query);
    }
}
