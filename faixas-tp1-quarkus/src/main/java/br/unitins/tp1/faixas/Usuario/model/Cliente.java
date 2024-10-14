package br.unitins.tp1.faixas.Usuario.model;

import br.unitins.tp1.faixas.Telefone.model.Telefone;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")

public class Cliente extends Pessoa {

    @Column(nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name="id_telefone")
        private Telefone telefone;

    @Column(nullable = false)
    private String cpf;


    @Enumerated(EnumType.STRING) 
    @Column(name = "sexo")
    private Sexo sexo;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Sexo getSexo() {
        return sexo;
    }
    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }
    public Telefone getTelefone() {
        return telefone;
    }
    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
        
    
}
