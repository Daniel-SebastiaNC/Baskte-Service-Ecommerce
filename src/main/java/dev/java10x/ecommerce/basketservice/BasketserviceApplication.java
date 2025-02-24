package dev.java10x.ecommerce.basketservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableCaching
@EnableFeignClients
public class BasketserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasketserviceApplication.class, args);
	}

}
