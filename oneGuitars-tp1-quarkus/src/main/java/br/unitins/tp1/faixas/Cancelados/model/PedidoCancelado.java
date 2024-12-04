package br.unitins.tp1.faixas.Cancelados.model;

import java.time.LocalDateTime;

import br.unitins.tp1.faixas.Pedido.model.Pedido;
import br.unitins.tp1.faixas.Usuario.service.DefaultEntity.model.DefaultEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PedidoCancelado extends DefaultEntity{
    @Column(nullable = false)
    LocalDateTime dataCancelamento;

    //pode ser que tenha que mudar para Pedido
   @ManyToOne(cascade = CascadeType.PERSIST)
   @JoinColumn(name = "pedido_cancelado_id", nullable = false)
    Pedido pedidoCancelado;

    public LocalDateTime getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(LocalDateTime dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public Pedido getPedidoCancelado() {
        return pedidoCancelado;
    }

    public void setPedidoCancelado(Pedido pedidoCancelado) {
        this.pedidoCancelado = pedidoCancelado;
    }

    

}
