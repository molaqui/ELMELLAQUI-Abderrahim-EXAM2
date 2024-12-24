
package org.example.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class OrderEvent {
    private Long productId;
    private int quantity;


    public OrderEvent(Long id, Long productId, int quantity) {
    }
}
