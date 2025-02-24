package dev.java10x.ecommerce.basketservice.service;

import dev.java10x.ecommerce.basketservice.client.response.PlatziProductResponse;
import dev.java10x.ecommerce.basketservice.controller.request.BasketRequest;
import dev.java10x.ecommerce.basketservice.controller.request.PaymentRequest;
import dev.java10x.ecommerce.basketservice.entity.Basket;
import dev.java10x.ecommerce.basketservice.entity.Product;
import dev.java10x.ecommerce.basketservice.entity.Status;
import dev.java10x.ecommerce.basketservice.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductService productService;

    public Basket createBasket(BasketRequest basketRequest){

        basketRepository.findByClientAndStatus(basketRequest.clientId(), Status.OPEN)
                .ifPresent( basket -> {
                    throw new IllegalArgumentException("There is already an open basket for this client");
                });

        List<Product> products = new ArrayList<>();


        basketRequest.products().forEach(product -> {
            PlatziProductResponse productById = productService.getProductById(product.id());
            products.add(
                    Product.builder()
                            .id(productById.id())
                            .title(productById.title())
                            .price(productById.price())
                            .quantity(product.quantity())
                            .build()
            );

        });

        Basket basket = Basket.builder()
                .client(basketRequest.clientId())
                .status(Status.OPEN)
                .products(products)
                .build();

        basket.calculateTotalPrice();
        return basketRepository.save(basket);

    }

    public Basket getBasketById(String id){
        return basketRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Basket not found"));
    }

    public Basket updateBasket(String id, BasketRequest basketRequest) {
        Basket basket = getBasketById(id);

        List<Product> products = new ArrayList<>();

        basketRequest.products().forEach(product -> {
            PlatziProductResponse productById = productService.getProductById(product.id());
            products.add(
                    Product.builder()
                            .id(productById.id())
                            .title(productById.title())
                            .price(productById.price())
                            .quantity(product.quantity())
                            .build()
            );

        });

        basket.setProducts(products);
        basket.calculateTotalPrice();

        return basketRepository.save(basket);

    }

    public Basket payBasket(String id, PaymentRequest paymentRequest) {
        Basket basket = getBasketById(id);
        basket.setPaymentMethod(paymentRequest.getPaymentMethod());
        basket.setStatus(Status.SOLD);

        return basketRepository.save(basket);
    }
}
