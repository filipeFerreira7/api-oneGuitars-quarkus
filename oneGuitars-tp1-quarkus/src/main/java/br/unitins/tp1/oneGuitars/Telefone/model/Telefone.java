package br.unitins.tp1.oneGuitars.Telefone.model;


import br.unitins.tp1.oneGuitars.Usuario.service.DefaultEntity.model.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Telefone extends DefaultEntity {
    @Column(nullable = false)
    private String dd;
    @Column(nullable = false)
    private String numero;


    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

}
