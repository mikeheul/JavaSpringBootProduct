package com.example.demo.controllers;

import com.example.demo.models.Category;
import com.example.demo.models.Product;
import com.example.demo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Category createCategory(@RequestBody Category Category) {
        return categoryService.saveCategory(Category);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.getCategoryById(id)
                .map(existingCategory -> {
                    category.setId(id);
                    return ResponseEntity.ok(categoryService.saveCategory(category));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (categoryService.getCategoryById(id).isPresent()) {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<Product>> getProductsByCategoryId(@PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(category -> ResponseEntity.ok(category.getProducts()))
                .orElse(ResponseEntity.notFound().build());
    }
}