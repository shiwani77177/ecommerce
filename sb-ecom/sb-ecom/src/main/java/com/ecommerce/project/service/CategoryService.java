package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;

import java.util.List;

public interface CategoryService {
    //static Category updateCategory(Category category, Long categoryId) {
       // return null;
    //}

    public static Category updateCategory(Category category, Long categoryId) {
        return null;
    }

    //Category updateCategory(Category category, Long categoryId);

    java.util.List<Category> getAllCategories();

    void createCategory(Category category);

    String deleteCategory(Long categoryId);
}