package br.unitins.tp1.faixas.Usuario.model;
import br.unitins.tp1.faixas.DefaultEntity.model.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
 public class Pessoa extends DefaultEntity{

    @Column(nullable = false)
    private String nome;
   
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    

}
