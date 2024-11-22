package br.unitins.tp1.faixas.Usuario.model;

import br.unitins.tp1.faixas.Cliente.service.DefaultEntity.model.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Usuario extends DefaultEntity {
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password")
    private String senha;
    
    @Column(name="id_perfil")
    private Perfil perfil;

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

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

}
