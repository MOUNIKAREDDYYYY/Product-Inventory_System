package com.mounika.product_inventory_system.exception;

/**
 * Exception thrown when an operation attempts to access a Product that does not exist.
 */
public class ProductNotFoundException extends RuntimeException {

    // Called with a Long ID  →  "Product with id 5 not found"
    public ProductNotFoundException(Long id) {
        super("Product with id " + id + " not found");
    }

    // Called with a custom message string
    public ProductNotFoundException(String message) {
        super(message);
    }
}
