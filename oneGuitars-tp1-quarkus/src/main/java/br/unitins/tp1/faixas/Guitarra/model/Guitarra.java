package br.unitins.tp1.faixas.Guitarra.model;

import br.unitins.tp1.faixas.Especificacao.model.Especificacao;
import br.unitins.tp1.faixas.Marca.model.Marca;
import br.unitins.tp1.faixas.Usuario.service.DefaultEntity.model.DefaultEntity;
// ORM = Object Relational Map
// JPA NÃO É VALIDADOR, APENAS MAPEIA
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Guitarra extends DefaultEntity {

    @ManyToOne
    @JoinColumn(name = "id_especificacao")
    private Especificacao especificacao;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;
    @Column(length = 60, nullable = false)
    private String nome;

    @Column(length = 20, nullable = false, unique = true)
    private String numeroSerie;

    @Column(nullable = false)
    private String cor;

    @Column(nullable = false)
    private Double preco;

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Especificacao getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(Especificacao especificacao) {
        this.especificacao = especificacao;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

}
