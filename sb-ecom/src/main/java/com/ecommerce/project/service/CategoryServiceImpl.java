package com.ecommerce.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {     //Checking if the category is empty or not
            throw new APIException("No category is created till now.");
        }
        return categories;
    }

    @Override
    public Category createCategory(Category category) {
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if (savedCategory != null) {    //Checking if the same category name already exists
            throw new APIException("Category with the name " + category.getCategoryName() + " already exists!!!");
        }
        //category.setCategoryId(nextId++);
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        // Existence check first so an unknown id is a clean 404 (not a stray insert).
        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("categoryId",categoryId, "Category"));
        category.setCategoryId(categoryId);
        return categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("categoryId",categoryId, "Category"));
        categoryRepository.delete(category);
        return "Category with categoryId " + categoryId + " deleted successfully";
    }
}