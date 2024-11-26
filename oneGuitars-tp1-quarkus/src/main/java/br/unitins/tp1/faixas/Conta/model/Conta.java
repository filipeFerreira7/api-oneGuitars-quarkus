package br.unitins.tp1.faixas.Conta.model;

import java.util.List;

import br.unitins.tp1.faixas.Usuario.service.DefaultEntity.model.DefaultEntity;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;

@Entity
public class Conta extends DefaultEntity {
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password")
    private String senha;
    
   @ElementCollection(targetClass = Perfil.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "conta_perfis", joinColumns = @JoinColumn(name = "conta_id"))
    @Column(name = "perfil")

    private List<Perfil> perfis;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }

   

}
