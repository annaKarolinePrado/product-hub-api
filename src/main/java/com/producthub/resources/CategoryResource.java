package com.producthub.resources;

import com.producthub.DTO.CategoryDTO;
import com.producthub.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO savedCategory = categoryService.saveCategory(categoryDTO);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CategoryDTO>> getCategoryById(@RequestParam Long id) {
        List<CategoryDTO> category = categoryService.findById(id);
        return ResponseEntity.ok(category);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.findAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PutMapping
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO updatedCategory = categoryService.updateCategory(categoryDTO);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteCategory(@RequestBody CategoryDTO category) {
        categoryService.deleteCategoryById(category.getId());
        return ResponseEntity.noContent().build();
    }

}
