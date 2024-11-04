package br.unitins.tp1.faixas.Endereco.repository;

import java.util.List;

import br.unitins.tp1.faixas.Endereco.model.Endereco;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnderecoRepository implements PanacheRepository<Endereco> {
    
    public List<Endereco> findByCep(String cep){
        return find("UPPER(endereco.cep) LIKE ?1",
        "%" + cep.toUpperCase() + "%").list();
        
    }
}
