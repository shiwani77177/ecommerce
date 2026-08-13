/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.ecommerce.project.exceptions;


public class ResourceNotFoundException extends RuntimeException {
  String resourceName;
  String field;
  String fieldName;
  Long fieldId;
  
    public ResourceNotFoundException() {
      
    }

    public ResourceNotFoundException(String field, String fieldName, String resourceName) {
        super(String.format("%s not found with %s: %s", resourceName, fieldName, field));
        this.field = field;
        this.fieldName = fieldName;
    }

    public ResourceNotFoundException(String field, Long fieldId, String resourceName) {
      super(String.format("%s not found with %s: %d", resourceName, field, fieldId));
        this.field = field;
        this.fieldId = fieldId;
    }
  
}
