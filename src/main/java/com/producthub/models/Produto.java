package com.producthub.models;

import jakarta.persistence.*;

@Entity
@Table(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private Double preco;
    private Long estoque;

    private boolean ativo = Boolean.TRUE;

    public Produto() {
    }

    public Produto(Long id, String nome, String email, Double preco, Long estoque, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.preco = preco;
        this.estoque = estoque;
        this.ativo = ativo;
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

    public boolean getAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
