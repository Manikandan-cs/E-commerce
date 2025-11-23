package com.mani.ecom.products_service.controller;

import com.mani.ecom.products_service.dto.UpdateQuantityRequest;
import com.mani.ecom.products_service.entity.Product;
import com.mani.ecom.products_service.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/e-com/product")
public class ProductController {

    public final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        String savedProduct = productService.addProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        /*
        Note
        return ResponseEntity.ok("Product Added Successfully");
        return ResponseEntity.status(HttpStatus.CREATED)
            .body("Product added successfully!");
         */
    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        System.out.println("in product service");
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/deleteProductById/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok("Product Deleted");
    }

    @PatchMapping("/updateProductById/{id}")
    public ResponseEntity<Product> updateProductById(@RequestBody Product product, @PathVariable Long id) {
        Product updatedProduct = productService.updateProductById(product, id);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> getAllProducts = productService.getAllProducts();
        return new ResponseEntity<>(getAllProducts, HttpStatus.OK);
    }

    @PostMapping("/updateQuantity")
    public ResponseEntity<String> updateQuantity(@RequestBody UpdateQuantityRequest request) {
        productService.reduceQuantity(request.getProductId(), request.getQuantity());
        return ResponseEntity.ok("Quantity updated");
    }

}