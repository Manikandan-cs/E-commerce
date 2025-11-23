package com.mani.ecom.products_service.service;

import com.mani.ecom.products_service.entity.Product;
import com.mani.ecom.products_service.exception.ProductNotFoundException;
import com.mani.ecom.products_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

   public final ProductRepository productRepository;

   public ProductService(ProductRepository productRepository){
       this.productRepository = productRepository;
   }

    public String addProduct(Product product) {
       productRepository.save(product);
       return "Product added in the store";
    }

    public Product getProductById(Long id) {
       return productRepository.findById(id)
               .orElseThrow(() -> new ProductNotFoundException("Product is not available for this id: "  + id));
    }

    public void deleteProductById(Long id) {
       productRepository.deleteById(id);
    }

    public Product updateProductById(Product product, Long id) {
        Product updateProduct =
                productRepository.findById(id)
               .orElseThrow(() -> new ProductNotFoundException("Product is not available for this id: " + product.getProductId()));
       updateProduct.setProductName(product.getProductName());
       updateProduct.setProductDescription(product.getProductDescription());

       return productRepository.save(updateProduct);
    }

    public List<Product> getAllProducts() {
       return productRepository.findAll();
    }

    public void reduceQuantity(Long productId, int qty) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getQuantity() < qty) {
            throw new RuntimeException("Insufficient stock");
        }

        product.setQuantity(product.getQuantity() - qty);

        productRepository.save(product);
    }

}
