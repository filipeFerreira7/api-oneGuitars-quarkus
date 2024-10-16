package br.unitins.tp1.faixas.Guitarra.repository;

import java.util.List;

import br.unitins.tp1.faixas.Guitarra.model.Guitarra;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
// Tudo que tem haver com banco de dados se remete aqui
@ApplicationScoped
public class GuitarraRepository implements PanacheRepository<Guitarra>{
    public List<Guitarra> findByNome(String nome) {    
       return find("SELECT g FROM Guitarra g WHERE g.nome LIKE ?1","%"+nome+"%").list();
    }

    public List<Guitarra> findBySku(String sku){
        return find("SELECT g From Guitarra g WHERE g.especficacao.sku LIKE ?1", "%"+sku+"%").list();
    }
}
