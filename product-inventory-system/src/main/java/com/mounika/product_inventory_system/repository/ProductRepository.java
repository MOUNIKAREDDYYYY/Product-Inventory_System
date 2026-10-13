
package com.mounika.product_inventory_system.repository;

import com.mounika.product_inventory_system.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository
        extends JpaRepository<Product, Long> {

    // Search products by exact name
    List<Product> findByProductNameIgnoreCase(String productName);

    // Search products by category
    List<Product> findByCategoryIgnoreCase(String category);

    // Search products whose names contain a keyword
    List<Product> findByProductNameContainingIgnoreCase(String keyword);

    // Search products within a price range
    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    // Search products with stock below a given quantity
    List<Product> findByStockLessThan(int stock);

    // Check whether a product name already exists
    boolean existsByProductNameIgnoreCase(String productName);

    // Get products sorted by name
    List<Product> findAllByOrderByProductNameAsc();

    // Count products in a category
    long countByCategoryIgnoreCase(String category);
}
