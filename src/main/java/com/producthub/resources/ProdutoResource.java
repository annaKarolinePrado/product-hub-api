package com.producthub.resources;

import com.producthub.DTO.ProdutoDTO;
import com.producthub.models.Produto;
import com.producthub.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping()
    public List<Produto> getAllProdutos() {
        return produtoService.getAllProdutos();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProdutoDTO>> getUsersByNome(@RequestParam String nome) {
        List<ProdutoDTO> produtos = produtoService.findUsersByNome(nome);
        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> createProduto(@RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO savedProduto = produtoService.saveProduto(produtoDTO);
        return ResponseEntity.ok(savedProduto);
    }

    @PutMapping()
    public ResponseEntity<ProdutoDTO> updateProduto(@RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO updatedProduto = produtoService.updateProduto(produtoDTO);
        return ResponseEntity.ok(updatedProduto);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteProduto(@RequestBody ProdutoDTO produto) {
        produtoService.deleteProdutoById(produto.getId());
        return ResponseEntity.noContent().build();
    }
}