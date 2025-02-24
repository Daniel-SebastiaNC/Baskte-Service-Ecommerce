package dev.java10x.ecommerce.basketservice.controller;

import dev.java10x.ecommerce.basketservice.client.response.PlatziProductResponse;
import dev.java10x.ecommerce.basketservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<PlatziProductResponse>> getAllProducts(){
        List<PlatziProductResponse> allProducts = productService.getAllProducts();
        return ResponseEntity.ok(allProducts);

    }

    @GetMapping("/{id}")
    public ResponseEntity<PlatziProductResponse> getProductById(@PathVariable Long id){

        return ResponseEntity.ok(productService.getProductById(id));

    }
}
