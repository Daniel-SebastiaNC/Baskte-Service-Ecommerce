package dev.java10x.ecommerce.basketservice.service;

import dev.java10x.ecommerce.basketservice.client.PlatziStoreClient;
import dev.java10x.ecommerce.basketservice.client.response.PlatziProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final PlatziStoreClient client;

    @Cacheable(value = "products")
    public List<PlatziProductResponse> getAllProducts(){
        log.info("Getting all products");
        return client.getAllProducts();
    }

    @Cacheable(value = "product", key = "#id")
    public PlatziProductResponse getProductById(Long id){
        log.info("Getting product with id: {}", id);
        return client.getProductById(id);
    }

}
