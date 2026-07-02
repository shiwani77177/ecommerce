package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final List<Category> categories = new ArrayList<>();
    private Long nextId = 1L;

    //@Override
    static Category updateCategory(Category category, Long categoryId) {
        Optional<Category> optionalCategory = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst();

        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            existingCategory.setCategoryName(category.getCategoryName());
            return existingCategory;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
    }

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);     //Managing IDs for products
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category categoryToDelete = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst()                   //For getting the category
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        boolean category = false;

        categories.remove(categoryToDelete);
        return "Category with categoryId " + categoryId + " deleted successfully";
    }

}
