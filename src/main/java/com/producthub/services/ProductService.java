package com.producthub.services;

import com.producthub.DTO.ProductDTO;
import com.producthub.exceptions.BussinesException;
import com.producthub.models.Product;
import com.producthub.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<ProductDTO> findProductByName(String name) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(name);
        return products.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        product = productRepository.save(product);
        return convertToDTO(product);
    }

    public ProductDTO updateProduct(ProductDTO product) {
        if (product.getId() == null) {
            throw new BussinesException("Campo id não informado.");
        }
        return saveProduct(product);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setActive(product.isActive());
        productDTO.setCategory(product.getCategory());
        productDTO.setImageUrl(product.getImageUrl());
        return productDTO;
    }

    private Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();

        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setActive(productDTO.isActive());
        product.setCategory(productDTO.getCategory());
        product.setImageUrl(productDTO.getImageUrl());
        return product;
    }

}