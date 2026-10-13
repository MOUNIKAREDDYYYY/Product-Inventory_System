package com.mounika.product_inventory_system.exception;

import java.time.LocalDateTime;

/**
 * Uniform error payload returned by the GlobalExceptionHandler for every
 * error response, so clients always get a consistent JSON structure:
 * {
 *   "timestamp": "...",
 *   "status":    404,
 *   "error":     "Not Found",
 *   "message":   "Product with id 5 not found",
 *   "path":      "/api/products/5"
 * }
 */
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public ErrorResponse() {}

    public ErrorResponse(int status, String error, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status    = status;
        this.error     = error;
        this.message   = message;
        this.path      = path;
    }

    //  Getters

    public LocalDateTime getTimestamp() { return timestamp; }
    public int getStatus()              { return status; }
    public String getError()            { return error; }
    public String getMessage()          { return message; }
    public String getPath()             { return path; }
}
