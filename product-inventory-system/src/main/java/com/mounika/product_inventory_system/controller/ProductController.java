
package com.mounika.product_inventory_system.controller;

import com.mounika.product_inventory_system.model.Product;
import com.mounika.product_inventory_system.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for Product CRUD operations.
 * Exceptions are handled centrally by GlobalExceptionHandler.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    // Constructor injection
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET /api/products - Get all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // POST /api/products/bulk - Save multiple products
    @PostMapping("/bulk")
    public ResponseEntity<List<Product>> addBulkProducts(
            @Valid @RequestBody List<@Valid Product> products) {

        List<Product> savedProducts =
                productService.addBulkProducts(products);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedProducts);
    }


    // GET /api/products/{id} - Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // POST /api/products - Add a new product
    @PostMapping
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {
        Product savedProduct = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    // PUT /api/products/{id} - Update an existing product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    // DELETE /api/products/{id} - Delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/products/search/name?productName=Laptop
    @GetMapping("/search/name")
    public ResponseEntity<List<Product>> searchByProductName(@RequestParam String productName) {
        return ResponseEntity.ok(productService.searchByProductName(productName));
    }

    // GET /api/products/search/category?category=Electronics
    @GetMapping("/search/category")
    public ResponseEntity<List<Product>> searchByCategory(@RequestParam String category) {
        return ResponseEntity.ok(productService.searchByCategory(category));
    }

    // GET /api/products/search/keyword?keyword=phone
    @GetMapping("/search/keyword")
    public ResponseEntity<List<Product>> searchByKeyword(@RequestParam String keyword) {
        return ResponseEntity.ok(productService.searchByKeyword(keyword));
    }

    // GET /api/products/search/price?minPrice=100&maxPrice=1000
    @GetMapping("/search/price")
    public ResponseEntity<List<Product>> searchByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        return ResponseEntity.ok(productService.searchByPriceRange(minPrice, maxPrice));
    }

    // GET /api/products/search/low-stock?stock=10
    @GetMapping("/search/low-stock")
    public ResponseEntity<List<Product>> searchByLowStock(@RequestParam int stock) {
        return ResponseEntity.ok(productService.searchByLowStock(stock));
    }

    // GET /api/products/sorted
    @GetMapping("/sorted")
    public ResponseEntity<List<Product>> getProductsSortedByName() {
        return ResponseEntity.ok(productService.getProductsSortedByName());
    }

    // GET /api/products/exists?productName=Laptop
    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkProductNameExists(@RequestParam String productName) {
        return ResponseEntity.ok(productService.checkProductNameExists(productName));
    }

    // GET /api/products/category-count?category=Electronics
    @GetMapping("/category-count")
    public ResponseEntity<Long> countProductsByCategory(@RequestParam String category) {
        return ResponseEntity.ok(productService.countProductsByCategory(category));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> patchProduct(@PathVariable Long id, @RequestBody Product updates) {
        return ResponseEntity.ok(productService.patchProduct(id, updates));
    }
}
