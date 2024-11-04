package br.unitins.tp1.faixas.Lote.model;

import java.time.LocalDate;

import br.unitins.tp1.faixas.Cliente.service.DefaultEntity.model.DefaultEntity;
import br.unitins.tp1.faixas.Guitarra.model.Guitarra;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity 
public class Lote extends DefaultEntity {
    
    @ManyToOne
    @JoinColumn(name = "id_guitarra")
    private Guitarra guitarra;

    private LocalDate data;

    private String codigo;
    
    private Integer estoque;

    public Guitarra getGuitarra() {
        return guitarra;
    }
    public void setGuitarra(Guitarra guitarra) {
        this.guitarra = guitarra;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public Integer getEstoque() {
        return estoque;
    }
    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    
}
