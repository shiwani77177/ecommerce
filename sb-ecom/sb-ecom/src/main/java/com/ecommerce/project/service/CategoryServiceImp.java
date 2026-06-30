package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

//import java.lang.ScopedValue;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.spi.ToolProvider;

import static java.util.Locale.filter;
import static java.util.spi.ToolProvider.findFirst;
import static org.apache.tomcat.websocket.Constants.FOUND;

@Service
public class CategoryServiceImp implements CategoryService{
    private final List<Category> categories = new ArrayList<>();
    private Long nextId = 1L;

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
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        boolean category = false;

        categories.remove(categoryToDelete);
        return "Category with categoryId " + categoryId + " deleted successfully";
    }

}
