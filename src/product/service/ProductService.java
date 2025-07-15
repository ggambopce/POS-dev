package product.service;

import product.entity.Product;

import java.util.List;

public interface ProductService {
    void registerProduct(Product product);

    List<Product> findAllProducts();

    void findByProductName();
}
