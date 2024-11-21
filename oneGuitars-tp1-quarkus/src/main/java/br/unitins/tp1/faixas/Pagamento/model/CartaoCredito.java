package br.unitins.tp1.faixas.Pagamento.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class CartaoCredito extends Pagamento {
    @Column(name="titular",nullable = false)
    private String nameOwner;
    @Column(name = "numero_cartao",nullable = false)
    private String number;
    @Column(name = "codigo_seguranca",nullable = false)
    private String cvv;
    @Column(name = "cpf_titular",nullable = false)
    private String cpf;
    @Column(name = "data_validade",nullable = false)
    private LocalDate validade;

    @OneToOne
    @JoinColumn(name = "id_bandeira")
    private BandeiraCartao bandeiraCartao;

    public String getNameOwner() {
        return nameOwner;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public BandeiraCartao getBandeiraCartao() {
        return bandeiraCartao;
    }

    public void setBandeiraCartao(BandeiraCartao bandeiraCartao) {
        this.bandeiraCartao = bandeiraCartao;
    }

}
