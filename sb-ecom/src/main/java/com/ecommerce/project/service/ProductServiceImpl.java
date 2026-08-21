package com.ecommerce.project.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.payload.ProductDTO;
import com.ecommerce.project.repositories.CategoryRepository;
import com.ecommerce.project.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public ProductDTO addProduct(Long categoryId, Product product) {  //add product with id and name of the product
    Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", categoryId, "categoryId"));

    product.setImage("default.png");    //Setting the product image
    product.setCategory(category);
    //Calculating the specialPrice
    double specialPrice = product.getPrice() - ((product.getDiscount() * 0.01) * product.getPrice());  //The second brace gives the discount price
    product.setSpecialPrice(specialPrice);
    Product savedProduct = productRepository.save(product);

    return modelMapper.map(savedProduct, ProductDTO.class);
  }
  
}
