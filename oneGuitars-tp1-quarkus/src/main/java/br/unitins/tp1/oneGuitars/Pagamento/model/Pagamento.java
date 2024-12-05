package br.unitins.tp1.oneGuitars.Pagamento.model;

import java.time.LocalDateTime;

import br.unitins.tp1.oneGuitars.Usuario.service.DefaultEntity.model.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento extends DefaultEntity {
    @Column()
    private boolean estaPago;
    @Column()
    private LocalDateTime dataPagamento;
    @Column(nullable = false)
    private Double valor;

    public boolean estaPago() {
        return estaPago;
    }

    public void setEstaPago(boolean estaPago) {
        this.estaPago = estaPago;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

}
