package dev.java10x.ecommerce.basketservice.controller;

import dev.java10x.ecommerce.basketservice.controller.request.BasketRequest;
import dev.java10x.ecommerce.basketservice.entity.Basket;
import dev.java10x.ecommerce.basketservice.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    @PostMapping("/add")
    public ResponseEntity<Basket> createBasket(@RequestBody BasketRequest basketRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(basketService.createBasket(basketRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Basket> getBasketById(@PathVariable String  id){
        return ResponseEntity.ok(basketService.getBasketById(id));
    }
}
