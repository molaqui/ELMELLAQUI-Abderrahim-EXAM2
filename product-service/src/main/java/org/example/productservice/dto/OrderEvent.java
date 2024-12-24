
package org.example.productservice.dto;

import lombok.Data;

@Data
public class OrderEvent {
    private Long productId;
    private int quantity;
}
