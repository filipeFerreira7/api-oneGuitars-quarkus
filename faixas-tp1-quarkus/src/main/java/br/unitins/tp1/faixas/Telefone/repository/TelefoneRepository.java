package br.unitins.tp1.faixas.Telefone.repository;

import java.util.List;

import br.unitins.tp1.faixas.Telefone.model.Telefone;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
// Tudo que tem haver com banco de dados se remete aqui
@ApplicationScoped
public class TelefoneRepository implements PanacheRepository<Telefone>{

    public List<Telefone> findByNumero(String numero) {    
        return find("numero LIKE ?1", "%" + numero + "%").list();
    }

}
