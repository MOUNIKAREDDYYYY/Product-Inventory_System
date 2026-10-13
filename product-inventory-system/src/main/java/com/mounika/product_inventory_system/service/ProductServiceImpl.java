package com.mounika.product_inventory_system.service;


import com.mounika.product_inventory_system.exception.ProductNotFoundException;
import com.mounika.product_inventory_system.model.Product;
import com.mounika.product_inventory_system.repository.ProductRepository;
import com.mounika.product_inventory_system.service.ProductService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    // Constructor injection
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //  Add a new product
    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> addBulkProducts(List<Product> products) {
        return productRepository.saveAll(products);
    }


    // Get all products
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get product by ID
    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product not found with id: " + productId));
    }

    //  Update product
    @Override
    public Product updateProduct(Long productId, Product product) {

        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product not found with id: " + productId));

        existingProduct.setProductName(product.getProductName());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setStock(product.getStock());
        existingProduct.setPrice(product.getPrice());

        return productRepository.save(existingProduct);
    }

    // Delete product
    @Override
    public void deleteProduct(Long productId) {

        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException(
                    "Product not found with id: " + productId);
        }

        productRepository.deleteById(productId);
    }

    // Search by exact product name
    @Override
    public List<Product> searchByProductName(String productName) {
        return productRepository.findByProductNameIgnoreCase(productName);
    }

    //  Search by category
    @Override
    public List<Product> searchByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }

    //  Search by keyword
    @Override
    public List<Product> searchByKeyword(String keyword) {
        return productRepository
                .findByProductNameContainingIgnoreCase(keyword);
    }

    //  Search by price range
    @Override
    public List<Product> searchByPriceRange(
            double minPrice, double maxPrice) {

        if (minPrice > maxPrice) {
            throw new IllegalArgumentException(
                    "Minimum price cannot exceed maximum price");
        }

        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    //  Search products with stock below a quantity
    @Override
    public List<Product> searchByLowStock(int stock) {
        return productRepository.findByStockLessThan(stock);
    }

    //  Get products sorted by name
    @Override
    public List<Product> getProductsSortedByName() {
        return productRepository.findAllByOrderByProductNameAsc();
    }

    //  Check whether product name exists
    @Override
    public boolean checkProductNameExists(String productName) {
        return productRepository
                .existsByProductNameIgnoreCase(productName);
    }

    //  Count products in a category
    @Override
    public long countProductsByCategory(String category) {
        return productRepository.countByCategoryIgnoreCase(category);
    }

    // Partially update an existing product
    @Override
    public Product patchProduct(Long id, Product updates) {

        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product not found with id: " + id));

        if (updates.getProductName() != null) {
            existing.setProductName(updates.getProductName());
        }

        if (updates.getCategory() != null) {
            existing.setCategory(updates.getCategory());
        }

        if (updates.getStock() != null) {
            existing.setStock(updates.getStock());
        }

        if (updates.getPrice() != null) {
            existing.setPrice(updates.getPrice());
        }

        return productRepository.save(existing);
    }
}
