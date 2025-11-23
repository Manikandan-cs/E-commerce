package com.mani.ecom.orders_service.openFeign;

import com.mani.ecom.orders_service.dto.ProductResponse;
import com.mani.ecom.orders_service.dto.UpdateQuantityRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name ="PRODUCTS-SERVICE")
public interface ProductClient {

    @GetMapping("/product/getProductById/{id}")
    ProductResponse getProductById(@PathVariable("id") Long id);

    @PostMapping("/product/updateQuantity")
    String updateQuantity(@RequestBody UpdateQuantityRequest request);
}
