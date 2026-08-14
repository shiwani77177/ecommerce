package com.ecommerce.project.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        //Sorting
        Sort sortByandOrder = sortOrder.equalsIgnoreCase("asc") 
        ? Sort.by(sortBy).ascending()
        : Sort.by(sortBy).descending(); 
        
        //Pagination logic to get categories according to the pageNumber and pageSize
        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByandOrder);
        Page<Category> categoryPage = categoryRepository.findAll(pageDetails); 

        List<Category> categories = categoryPage.getContent();
        if (categories.isEmpty()) {     //Checking if the category is empty or not
            throw new APIException("No category is created till now.");
        }

            List<CategoryDTO> categoryDTOS = categories.stream()
                    .map(category -> modelMapper.map(category, CategoryDTO.class))
                    .toList();

            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setCategories(categoryDTOS);
            //Setting parameters
            categoryResponse.setPageNumber(categoryPage.getNumber()); 
            categoryResponse.setPageSize(categoryPage.getSize());
            categoryResponse.setTotalElements(categoryPage.getTotalElements());
            categoryResponse.setTotalPages(categoryPage.getTotalPages());
            categoryResponse.setLastPage(categoryPage.isLast());
            return categoryResponse;
        
    }


    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category categoryFromDB = categoryRepository.findByCategoryName(categoryDTO.getCategoryName());
        if (categoryFromDB != null) {  //Checking if the same category name already exists
            throw new APIException("Category with the name " + categoryDTO.getCategoryName() + " already exists!!!");
        }

        Category savedCategory = categoryRepository.save(category);
        CategoryDTO savedCategoryDTO = modelMapper.map(savedCategory, CategoryDTO.class);
        return savedCategoryDTO;
    }


    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {
        // Existence check first so an unknown id is a clean 404 (not a stray insert).
        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("categoryId",categoryId, "Category"));

        Category category = modelMapper.map(categoryDTO, Category.class);
        category.setCategoryId(categoryId);
        Object savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }


    @Override
    public CategoryDTO deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("categoryId",categoryId, "Category"));

        categoryRepository.delete(category);
        return modelMapper.map(category, CategoryDTO.class);
    }

}

