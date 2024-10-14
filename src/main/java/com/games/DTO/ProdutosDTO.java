package com.games.DTO;

public class ProdutosDTO {
    private Long id;
    private String nome;
    private String email;
    private Double preco;
    private Long estoque;

    public ProdutosDTO() {
    }

    public ProdutosDTO(Long id, String nome, String email, Double preco, Long estoque) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.preco = preco;
        this.estoque = estoque;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Long getEstoque() {
        return estoque;
    }
    public void setEstoque(Long estoque) {
        this.estoque = estoque;
    }
}
