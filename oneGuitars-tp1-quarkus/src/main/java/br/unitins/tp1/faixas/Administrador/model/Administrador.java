package br.unitins.tp1.faixas.Administrador.model;
import br.unitins.tp1.faixas.Cliente.service.DefaultEntity.model.DefaultEntity;
import br.unitins.tp1.faixas.Usuario.model.PessoaFisica;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Administrador extends DefaultEntity{

    @Column(name = "codigo_administrador")
    private String codigoAdm;

    @OneToOne
    @JoinColumn(name = "id_pessoa_fisica", unique = true)
    private PessoaFisica pessoaFisica;

    public String getCodigoAdm() {
        return codigoAdm;
    }

    public void setCodigoAdm(String codigoAdm) {
        this.codigoAdm = codigoAdm;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }
    
}