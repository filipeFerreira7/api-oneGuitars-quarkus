package br.unitins.tp1.oneGuitars.Marca.repository;

import br.unitins.tp1.oneGuitars.Marca.model.Marca;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
// Tudo que tem haver com banco de dados se remete aqui
@ApplicationScoped
public class MarcaRepository implements PanacheRepository<Marca>{
    public Marca findByNome(String nome) {    
       return find("SELECT m FROM Marca m WHERE m.nome LIKE ?1",nome).firstResult();
    }
}
