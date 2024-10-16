package br.unitins.tp1.faixas.Usuario.repository;

import java.util.List;

import br.unitins.tp1.faixas.Usuario.model.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
// Tudo que tem haver com banco de dados se remete aqui
@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente>{

    public List<Cliente> findByNome(String nome) {    
       return find("SELECT c FROM Cliente c WHERE c.nome LIKE ?1","%"+ nome +"%").list();
    }

}