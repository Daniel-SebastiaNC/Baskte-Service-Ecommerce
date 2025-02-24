package dev.java10x.ecommerce.basketservice.client.response;

import java.io.Serializable;
import java.math.BigDecimal;

public record PlatziProductResponse(Long id,
                                    String title,
                                    BigDecimal price) implements Serializable {
}
