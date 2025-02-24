package dev.java10x.ecommerce.basketservice.controller.request;

import java.util.List;

public record BasketRequest(Long clientId, List<ProductRequest> products) {
}
