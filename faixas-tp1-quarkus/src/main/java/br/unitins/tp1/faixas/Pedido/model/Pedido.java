package br.unitins.tp1.faixas.Pedido.model;

import java.time.LocalDateTime;
import java.util.List;

import br.unitins.tp1.faixas.DefaultEntity.model.DefaultEntity;
import br.unitins.tp1.faixas.Usuario.model.Usuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity 
public class Pedido extends DefaultEntity {

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario user;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "id_pedido",nullable = false)
    private List<ItemPedido> listaItemPedido;

    @Column
    private LocalDateTime dataCompra;

    @Column
    private Double valorTotal;

    
    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public List<ItemPedido> getListaItemPedido() {
        return listaItemPedido;
    }

    public void setListaItemPedido(List<ItemPedido> listaItemPedido) {
        this.listaItemPedido = listaItemPedido;
    }

    
}
