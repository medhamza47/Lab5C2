package com.microservice.product;

import com.microservice.product.config.AppConfig;
import com.microservice.product.entity.Product;
import com.microservice.product.service.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductService productService = context.getBean(ProductService.class);

        Product p1 = new Product("Microservice Gateway", 149.99);
        Product p2 = new Product("Message Broker", 89.50);
        productService.addProduct(p1);
        productService.addProduct(p2);
        System.out.println("Produit ajoute avec succes ! Total en base : " + productService.getAllProducts().size());

        List<Product> products = productService.getAllProducts();
        for (Product product : products) {
            System.out.println(product);
        }

        Product found = productService.getProduct(p1.getId());
        found.setPrice(139.99);
        productService.updateProduct(found);
        System.out.println("Produit mis a jour : " + productService.getProduct(p1.getId()));

        productService.deleteProduct(p2);
        System.out.println("Produit supprime, total en base : " + productService.getAllProducts().size());

        context.close();
    }
}
