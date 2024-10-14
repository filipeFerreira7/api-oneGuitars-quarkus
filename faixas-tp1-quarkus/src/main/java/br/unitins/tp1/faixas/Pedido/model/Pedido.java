package br.unitins.tp1.faixas.Pedido.model;

import br.unitins.tp1.faixas.DefaultEntity.model.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.time.LocalDateTime;


@Entity 
public class Pedido extends DefaultEntity {
    
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

    
}
