
package com.mounika.product_inventory_system.service;

import com.mounika.product_inventory_system.model.Product;
import java.util.List;

public interface ProductService {

    // Add a new product
    Product addProduct(Product product);

    // Save multiple products
    List<Product> addBulkProducts(List<Product> products);

    // Retrieve all products
    List<Product> getAllProducts();

    // Retrieve a product by ID
    Product getProductById(Long productId);

    // Update an existing product
    Product updateProduct(Long productId, Product product);

    // Delete a product by ID
    void deleteProduct(Long productId);

    // Search by exact product name
    List<Product> searchByProductName(String productName);

    // Search by category
    List<Product> searchByCategory(String category);

    // Search by product name keyword
    List<Product> searchByKeyword(String keyword);

    // Search within a price range
    List<Product> searchByPriceRange(
            double minPrice, double maxPrice);

    // Find products with low stock
    List<Product> searchByLowStock(int stock);

    // Get products sorted alphabetically
    List<Product> getProductsSortedByName();

    // Check whether product name exists
    boolean checkProductNameExists(String productName);

    // Count products in a category
    long countProductsByCategory(String category);

    // Partially update an existing product
    Product patchProduct(Long id, Product updates);
}
