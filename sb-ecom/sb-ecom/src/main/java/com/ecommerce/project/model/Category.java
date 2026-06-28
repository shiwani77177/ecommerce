package com.ecommerce.project.model;

public class Category {
    private Long categoryId;
    private String categoryName;

    // 1. CRITICAL: You must have a no-argument constructor
    public Category() {
    }

    // 2. Optional: A parameterized constructor (if you need it)
    public Category(Long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    // 3. CRITICAL: Standard Getters and Setters must be present
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
