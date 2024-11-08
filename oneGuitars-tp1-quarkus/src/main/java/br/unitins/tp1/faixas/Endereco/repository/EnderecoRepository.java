package br.unitins.tp1.faixas.Endereco.repository;

import br.unitins.tp1.faixas.Endereco.model.Endereco;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnderecoRepository implements PanacheRepository<Endereco> {
    
   public Endereco findByCep(String cep){
    
        return find("SELECT e FROM Endereco e WHERE e.cep LIKE ?1", "%" + cep + "%").firstResult();
    }
    


}
