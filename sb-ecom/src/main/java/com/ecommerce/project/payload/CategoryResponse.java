package com.ecommerce.project.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
     private List<CategoryDTO> content;
     private Integer pageNumber;   //Pagination parameters for frontend
     private Integer pageSize;
     private Long totalElements;
     private Integer totalPages;
     private boolean lastPage;

    public void setCategories(List<CategoryDTO> categoryDTOS) {
        this.content = categoryDTOS;
    }
}
