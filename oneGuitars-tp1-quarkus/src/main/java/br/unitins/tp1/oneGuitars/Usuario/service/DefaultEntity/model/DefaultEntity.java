package br.unitins.tp1.oneGuitars.Usuario.service.DefaultEntity.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass // diz que todos os atributos da herança vão ser colocados na tabela principal, não terei uma tabela 'DefaultEntity'
public class DefaultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
