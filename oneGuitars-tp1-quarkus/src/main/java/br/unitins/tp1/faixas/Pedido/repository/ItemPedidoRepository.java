package br.unitins.tp1.faixas.Pedido.repository;

import br.unitins.tp1.faixas.Guitarra.model.Guitarra;
import br.unitins.tp1.faixas.Pedido.model.ItemPedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemPedidoRepository implements PanacheRepository<ItemPedido> {
    public ItemPedido findByProduto (Guitarra guitarra){
        if(guitarra != null){
            return find("lote.guitarra =?1",guitarra).firstResult();
        }
        return null;
    }
}
