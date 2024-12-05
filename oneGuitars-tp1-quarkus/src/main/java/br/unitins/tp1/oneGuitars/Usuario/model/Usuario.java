package br.unitins.tp1.oneGuitars.Usuario.model;

import br.unitins.tp1.oneGuitars.PessoaFisica.model.PessoaFisica;
import br.unitins.tp1.oneGuitars.Usuario.service.DefaultEntity.model.DefaultEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name = "Usuario")
public class Usuario extends DefaultEntity {

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_pessoa_fisica", unique = true, nullable = false)
    private PessoaFisica pessoaFisica;

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

}
