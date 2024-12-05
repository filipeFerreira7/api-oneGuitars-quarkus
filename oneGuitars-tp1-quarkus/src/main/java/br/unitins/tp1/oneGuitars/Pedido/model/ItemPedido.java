package br.unitins.tp1.oneGuitars.Pedido.model;

import br.unitins.tp1.oneGuitars.Lote.model.Lote;
import br.unitins.tp1.oneGuitars.Usuario.service.DefaultEntity.model.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity 
public class ItemPedido extends DefaultEntity {

    @ManyToOne
    @JoinColumn(name ="id_lote",nullable = false)
    private Lote lote;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private Integer quantidade;
        
    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

  

    

    
}
