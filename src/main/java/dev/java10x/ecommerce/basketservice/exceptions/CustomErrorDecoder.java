package dev.java10x.ecommerce.basketservice.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()){
            case 400:
                return new DataNotFoundException("Product Not Found");
            default:
                return new Exception("Exception while getting product");
        }
    }
}
