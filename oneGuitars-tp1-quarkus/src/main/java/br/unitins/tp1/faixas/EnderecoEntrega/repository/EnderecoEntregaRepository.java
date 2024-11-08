package br.unitins.tp1.faixas.EnderecoEntrega.repository;

import br.unitins.tp1.faixas.EnderecoEntrega.model.EnderecoEntrega;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class EnderecoEntregaRepository implements PanacheRepository<EnderecoEntrega> {
    
    public EnderecoEntrega findByCep(String cep){
        return find("UPPER(enderecoEntrega.cep) = ?1",
        "%" + cep.toUpperCase() + "%").firstResult();
    }
}
