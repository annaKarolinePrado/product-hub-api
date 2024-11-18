package com.producthub.services;

import com.producthub.DTO.CategoryDTO;
import com.producthub.exceptions.BussinesException;
import com.producthub.models.Category;
import com.producthub.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    private CategoryDTO convertToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        categoryDTO.setActive(category.isActive());
        return categoryDTO;
    }

    private Category convertToEntity(CategoryDTO dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setActive(dto.isActive());
        return category;
    }

    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        Category category = convertToEntity(categoryDTO);
        category = categoryRepository.save(category);
        return convertToDTO(category);
    }

    public List<CategoryDTO> findById(Long id) {
        List<Category> category = categoryRepository.findCategoryById(id);
        return category.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<CategoryDTO> findAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CategoryDTO updateCategory(CategoryDTO category) {
        if (category.getId() == null) {
            throw new BussinesException("Campo id não informado.");
        }
        return saveCategory(category);
    }

    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }


}
