package com.producthub.resources;

import com.producthub.DTO.ProductDTO;
import com.producthub.models.Product;
import com.producthub.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> getUsersByName(@RequestParam String name) {
        List<ProductDTO> products = productService.findProductByName(name);
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO savedProduct = productService.saveProduct(productDTO);
        return ResponseEntity.ok(savedProduct);
    }

    @PutMapping()
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.updateProduct(productDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteProduct(@RequestBody ProductDTO product) {
        productService.deleteProductById(product.getId());
        return ResponseEntity.noContent().build();
    }
}