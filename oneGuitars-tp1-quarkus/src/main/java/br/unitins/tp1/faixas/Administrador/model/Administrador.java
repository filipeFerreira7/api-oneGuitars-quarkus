package br.unitins.tp1.faixas.Administrador.model;
import java.time.LocalDate;
import br.unitins.tp1.faixas.Cliente.service.DefaultEntity.model.DefaultEntity;
import br.unitins.tp1.faixas.Usuario.model.PessoaFisica;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Administrador extends DefaultEntity{

    @Column(name = "codigo_contrato")
    private String codigoContrato;

    @Column(name = "data_admissao")
    private LocalDate dataAdmissao;

    @OneToOne
    @JoinColumn(name = "id_pessoa_fisica", unique = true)
    private PessoaFisica pessoaFisica;

    public String getCodigoContrato() {
        return codigoContrato;
    }

    public void setCodigoContrato(String codigoContrato) {
        this.codigoContrato = codigoContrato;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }
    
}