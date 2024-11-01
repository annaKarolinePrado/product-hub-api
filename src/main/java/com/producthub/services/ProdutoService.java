package com.producthub.services;

import com.producthub.DTO.ProdutoDTO;
import com.producthub.exceptions.BussinesException;
import com.producthub.models.Produto;
import com.producthub.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    public List<ProdutoDTO> findUsersByNome(String nome) {
        List<Produto> produtos = produtoRepository.findByNomeContainingIgnoreCase(nome);
        return produtos.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ProdutoDTO saveProduto(ProdutoDTO produtoDTO) {
        Produto produto = convertToEntity(produtoDTO);
        produto = produtoRepository.save(produto);
        return convertToDTO(produto);
    }

    public ProdutoDTO updateProduto(ProdutoDTO produto) {
        if (produto.getId() == null) {
            throw new BussinesException("Campo id não informado.");
        }
        return saveProduto(produto);
    }

    public void deleteProdutoById(Long id) {
        produtoRepository.deleteById(id);
    }

    private ProdutoDTO convertToDTO(Produto produto) {
        ProdutoDTO produtoDTO = new ProdutoDTO();

        produtoDTO.setId(produto.getId());
        produtoDTO.setNome(produto.getNome());
        produtoDTO.setEmail(produto.getEmail());
        produtoDTO.setPreco(produto.getPreco());
        produtoDTO.setEstoque(produto.getEstoque());
        produtoDTO.setAtivo(produto.getAtivo());
        return produtoDTO;
    }

    private Produto convertToEntity(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();

        produto.setId(produtoDTO.getId());
        produto.setNome(produtoDTO.getNome());
        produto.setEmail(produtoDTO.getEmail());
        produto.setPreco(produtoDTO.getPreco());
        produto.setEstoque(produtoDTO.getEstoque());
        produto.setAtivo(produtoDTO.isAtivo());
        return produto;
    }
}