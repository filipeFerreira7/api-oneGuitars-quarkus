package br.unitins.tp1.oneGuitars.Endereco.repository;

import br.unitins.tp1.oneGuitars.Endereco.model.Endereco;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class EnderecoRepository implements PanacheRepository<Endereco> {
    
    public Endereco findByCep(String cep){
        return find("UPPER(endereco.cep) = ?1",
        "%" + cep.toUpperCase() + "%").firstResult();
    }
}
