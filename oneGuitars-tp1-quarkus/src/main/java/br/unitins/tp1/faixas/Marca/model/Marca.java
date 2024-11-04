package br.unitins.tp1.faixas.Marca.model;
import br.unitins.tp1.faixas.Cliente.service.DefaultEntity.model.DefaultEntity;
// ORM = Object Relational Map
// JPA NÃO É VALIDADOR, APENAS MAPEIA
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity 
public class Marca extends DefaultEntity {

    @Column(name = "nome")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
}