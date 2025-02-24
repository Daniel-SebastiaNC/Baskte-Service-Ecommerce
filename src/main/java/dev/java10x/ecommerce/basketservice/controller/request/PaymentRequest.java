package dev.java10x.ecommerce.basketservice.controller.request;

import dev.java10x.ecommerce.basketservice.entity.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private PaymentMethod paymentMethod;
}
