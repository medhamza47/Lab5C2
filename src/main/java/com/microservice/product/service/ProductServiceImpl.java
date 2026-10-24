package com.microservice.product.service;

import com.microservice.product.dao.IDao;
import com.microservice.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private IDao<Product> productDao;

    @Transactional
    @Override
    public void addProduct(Product product) {
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Le prix ne peut pas etre negatif");
        }
        productDao.save(product);
    }

    @Transactional
    @Override
    public void updateProduct(Product product) {
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Le prix ne peut pas etre negatif");
        }
        productDao.update(product);
    }

    @Transactional
    @Override
    public void deleteProduct(Product product) {
        productDao.delete(product);
    }

    @Transactional(readOnly = true)
    @Override
    public Product getProduct(Long id) {
        return productDao.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }
}
