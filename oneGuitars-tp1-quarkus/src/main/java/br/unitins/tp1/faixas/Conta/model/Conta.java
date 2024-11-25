package br.unitins.tp1.faixas.Conta.model;

import java.util.List;

import br.unitins.tp1.faixas.Usuario.service.DefaultEntity.model.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Conta extends DefaultEntity {
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password")
    private String senha;
    
    @OneToMany // uma conta tem varios perfis(podendo ser adm ou client)
    @JoinColumn(name="id_perfil")
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
