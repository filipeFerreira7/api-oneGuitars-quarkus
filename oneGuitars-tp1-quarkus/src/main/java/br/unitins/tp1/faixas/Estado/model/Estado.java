package br.unitins.tp1.faixas.Estado.model;

import br.unitins.tp1.faixas.DefaultEntity.model.DefaultEntity;
// ORM = Object Relational Map
// JPA NÃO É VALIDADOR, APENAS MAPEIA
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity 
public class Estado extends DefaultEntity{


    @Column(length = 60, nullable = false)
    private String nome;

    @Column(length = 2, nullable = false)
    private String sigla;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    
}
