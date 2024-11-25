package br.unitins.tp1.faixas.PessoaFisica.model;

import java.time.LocalDate;

import br.unitins.tp1.faixas.Cliente.service.DefaultEntity.model.DefaultEntity;
import br.unitins.tp1.faixas.Conta.model.Conta;
import br.unitins.tp1.faixas.Telefone.model.Telefone;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class PessoaFisica extends DefaultEntity {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @ManyToOne
    @JoinColumn(name = "id_telefone")
    private Telefone telefone;

    @Column(unique = true, nullable = false)
    private String cpf;

    @OneToOne()
    @JoinColumn(name = "id_usuario", unique = true)
    private Conta usuario;

    private String nomeImagem;

    Sexo sexo;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Conta getUsuario() {
        return usuario;
    }

    public void setUsuario(Conta usuario) {
        this.usuario = usuario;
    }

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

}