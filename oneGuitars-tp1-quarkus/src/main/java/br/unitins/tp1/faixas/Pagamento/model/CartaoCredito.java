package br.unitins.tp1.faixas.Pagamento.model;

import java.time.LocalDate;

import br.unitins.tp1.faixas.PessoaFisica.converterjpa.BandeiraCartaoConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;

@Entity
public class CartaoCredito extends Pagamento {
    @Column(name = "titular", nullable = false)
    private String nomeTitular;

    @Column(name = "numero_cartao", nullable = false)
    private String numeroCartao;

    @Column(name = "codigo_seguranca", nullable = false)
    private String cvv;

    @Column(name = "cpftitular_titular", nullable = false)
    private String cpfTitular;
    
    @Column(name = "data_validade", nullable = false)
    private LocalDate validade;

    @Column(name = "saldo_cartao", nullable = false)
    private Double saldoCartao;

     @Convert(converter = BandeiraCartaoConverter.class) 
    private BandeiraCartao bandeiraCartao;

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numerocartao) {
        this.numeroCartao = numerocartao;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCpfTitular() {
        return cpfTitular;
    }

    public void setCpfTitular(String cpftitular) {
        this.cpfTitular = cpftitular;
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

    public Double getSaldoCartao() {
        return saldoCartao;
    }

    public void setSaldoCartao(Double saldoCartao) {
        this.saldoCartao = saldoCartao;
    }

}
